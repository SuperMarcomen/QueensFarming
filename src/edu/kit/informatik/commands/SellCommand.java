package edu.kit.informatik.commands;

import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.utility.ErrorLogger;

import java.util.List;

/**
 * A class to handle the sell command.
 *
 * @author uswry
 * @version 1.0
 */
public class SellCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(all)|((mushroom|carrot|salad|tomato)\\s?)+|^$";
    private static final String CORRECT_FORMAT = "all or [mushroom|carrot|salad|tomato]";
    private static final String NOT_ENOUGH = "You don't have enough vegetables to sell!";
    private final Match match;
    private final Market market;

    /**
     * Initializing the needed constants and calling the super constructor.
     *
     * @param match - An instance of the Match class
     * @param market - An instance of the Market class
     */
    public SellCommand(Match match, Market market) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.match = match;
        this.market = market;
    }

    @Override
    protected List<String> execute() {
        if (input.length() > 0 && input.isBlank()) return List.of(getCorrectFormat());
        Player player = match.getCurrentPlayer();
        if (!market.canSell(player, input)) return List.of(ErrorLogger.format(NOT_ENOUGH));
        List<String> strings = List.of(market.sell(player, input));
        match.reduceActions();
        return strings;
    }
}
