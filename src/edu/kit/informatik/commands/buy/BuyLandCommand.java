package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.InputCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.utility.ErrorLogger;

import java.util.List;

/**
 * A class to handle the buy land command.
 *
 * @author uswry
 * @version 1.0
 */
public class BuyLandCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "-?\\d+ \\d+";
    private static final String CORRECT_FORMAT = "buy land [x-coordinate] [y-coordinate]";
    private static final String NO_FIELDS_AVAILABLE = "There aren't any more fields to buy!";
    private static final String NOT_ENOUGH_MONEY = "You don't have enough money!";
    private static final String NOT_REACHABLE = "You can't reach this field!";
    private static final String ALREADY_OCCUPIED = "This field is already occupied!";
    private final Match match;
    private final Market market;

    /**
     * Initializing the regex pattern
     *
     * @param match - An instance of the Match class
     * @param market - An instance of the Market class
     */
    public BuyLandCommand(Market market, Match match) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.market = market;
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        Player player = match.getCurrentPlayer();
        String[] args = input.split(" ");
        int x = parseInput(args[0]);
        int y = parseInput(args[1]);

        if (!market.areThereFieldsLeft()) return List.of(ErrorLogger.format(NO_FIELDS_AVAILABLE));
        if (player.isFieldAvailable(x, y) || (x == 0 && y == 0)) return List.of(ErrorLogger.format(ALREADY_OCCUPIED));
        if (!player.canReachField(x, y)) return List.of(ErrorLogger.format(NOT_REACHABLE));
        if (!player.hasEnoughMoney(market.getFieldPrice(x, y))) return List.of(ErrorLogger.format(NOT_ENOUGH_MONEY));
        List<String> strings = List.of(market.buyField(player, x, y));
        match.reduceActions();
        return strings;
    }
}
