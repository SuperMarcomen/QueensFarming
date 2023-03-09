package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.InputCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.List;

public class BuyVegetableCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(mushroom|carrot|salad|tomato)";
    private static final String CORRECT_FORMAT = "buy vegetable [mushroom|carrot|salad|tomato]";
    private static final String NOT_ENOUGH_MONEY = "Error: You don't have enough money!";
    private final Market market;
    private final Match match;

    /**
     * Initializing the regex pattern
     */
    public BuyVegetableCommand(Market market, Match match) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.market = market;
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        Vegetable vegetable = Vegetable.fromString(input);
        Player player = match.getCurrentPlayer();
        int price = market.getPriceOf(vegetable);
        if (!player.hasEnoughMoney(price)) return List.of(NOT_ENOUGH_MONEY);
        List<String> strings = List.of(market.buy(player, vegetable));
        match.reduceActions();
        return strings;
    }
}
