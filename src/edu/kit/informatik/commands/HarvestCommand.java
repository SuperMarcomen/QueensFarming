package edu.kit.informatik.commands;

import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;
import edu.kit.informatik.game.utility.ErrorLogger;

import java.util.List;

/**
 * A class to handle the harvest command.
 *
 * @author uswry
 * @version 1.0
 */
public class HarvestCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "-?\\d+ -?\\d+ [1-8]";
    private static final String CORRECT_FORMAT = "harvest [x-coordinate] [y-coordinate] [quantity (At least one)]";
    private static final String FIELD_NOT_AVAILABLE = "There is no field at these coordinates!";
    private static final String FIELD_EMPTY = "The field at these coordinates is empty"
            + " or there aren't enough vegetables!";
    private static final String HARVEST = "You have harvested %d %s.";
    private final Match match;

    /**
     * Initializing the needed constants and calling the super constructor.
     *
     * @param match - An instance of the Match class
     */
    public HarvestCommand(Match match) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.match = match;
    }


    @Override
    protected List<String> execute() {
        Player player = match.getCurrentPlayer();
        String[] arguments = input.split(" ");
        int x = parseInput(arguments[0]);
        int y = parseInput(arguments[1]);
        int quantity = parseInput(arguments[2]);

        if (!player.isFieldAvailable(x, y)) return List.of(ErrorLogger.format(FIELD_NOT_AVAILABLE));
        if (!player.hasFieldEnoughVegetables(x, y, quantity)) return List.of(ErrorLogger.format(FIELD_EMPTY));

        Vegetable vegetable = player.getVegetableAt(x, y);
        String message = String.format(HARVEST, quantity, vegetable.getNameFromQuantity(quantity));

        player.harvest(x, y, quantity);
        List<String> strings = List.of(message);
        match.reduceActions();
        return strings;
    }
}
