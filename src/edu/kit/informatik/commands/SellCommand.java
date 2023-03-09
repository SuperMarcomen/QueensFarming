package edu.kit.informatik.commands;

import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;

import java.util.List;

public class SellCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(all)|((mushroom|carrot|salad|tomato)\\s?)+|^$";
    private static final String CORRECT_FORMAT = "all or [mushroom|carrot|salad|tomato]";
    private static final String NOT_ENOUGH = "Error: You don't have enough vegetables to sell!";
    private final Match match;
    private final Market market;

    /**
     * Initializing the regex pattern
     *
     * @param match
     * @param market
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
        if (!market.canSell(player, input)) return List.of(NOT_ENOUGH);
        List<String> strings = List.of(market.sell(player, input));
        match.reduceActions();
        return strings;
    }
}
