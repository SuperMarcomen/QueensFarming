package edu.kit.informatik.commands;

import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.Collections;
import java.util.List;

/**
 * A class to handle the plant command.
 *
 * @author uswry
 * @version 1.0
 */
public class PlantCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "-?\\d+ -?\\d+ (tomato|mushroom|carrot|salad)"; // TODO negative y
    private static final String CORRECT_FORMAT = "plant [x-coordinate] [y-coordinate] [vegetable name]";
    private static final String FIELD_NOT_AVAILABLE = "Error: There is no field at these coordinates!";
    private static final String FIELD_NOT_EMPTY = "Error: The field at these coordinates is not empty!";
    private static final String VEGETABLE_NOT_AVAILABLE = "Error: You don't have this vegetable!";
    private static final String VEGETABLE_NOT_PLANTABLE = "Error: You can't plant this vegetable on this field!";
    private final Match match;

    /**
     * Initializing the needed constants and calling the super constructor.
     *
     * @param match - An instance of the Match class
     */
    public PlantCommand(Match match) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        Player player = match.getCurrentPlayer();
        String[] arguments = input.split(" ");
        int x = Integer.parseInt(arguments[0]);
        int y = Integer.parseInt(arguments[1]);
        Vegetable vegetable = Vegetable.fromString(arguments[2]);

        if (!player.isFieldAvailable(x, y)) return List.of(FIELD_NOT_AVAILABLE);
        if (!player.isFieldEmpty(x, y)) return List.of(FIELD_NOT_EMPTY);
        if (!player.hasVegetable(vegetable)) return List.of(VEGETABLE_NOT_AVAILABLE);
        if (!player.canPlantOnField(x, y, vegetable)) return List.of(VEGETABLE_NOT_PLANTABLE);

        player.plant(x, y, vegetable);
        match.reduceActions();
        return Collections.emptyList();
    }
}
