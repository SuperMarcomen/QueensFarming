package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.InputCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;
import edu.kit.informatik.game.utility.ErrorLogger;

import java.util.List;

/**
 * A class to handle the buy vegetable command.
 *
 * @author uswry
 * @version 1.0
 */
public class BuyVegetableCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(mushroom|carrot|salad|tomato)";
    private static final String CORRECT_FORMAT = "buy vegetable [mushroom|carrot|salad|tomato]";
    private static final String NOT_ENOUGH_MONEY = "You don't have enough money!";
    private final Market market;
    private final Match match;

    /**
     * Initializing the regex pattern and local constants.
     *
     * @param market - An instance of the Market class
     * @param match - An instance of the Match class
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
        if (!player.hasEnoughMoney(price)) return List.of(ErrorLogger.format(NOT_ENOUGH_MONEY));
        List<String> strings = List.of(market.buy(player, vegetable));
        match.reduceActions();
        return strings;
    }
}
