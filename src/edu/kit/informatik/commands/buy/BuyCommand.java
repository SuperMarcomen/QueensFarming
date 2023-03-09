package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;

/**
 * A class to handle the buy command.
 *
 * @author uswry
 * @version 1.0
 */
public class BuyCommand extends TreeCommand {


    /**
     * Registering the subcommands.
     *
     * @param match - An instance of the Match class
     * @param market - An instance of the Market class
     */
    public BuyCommand(Match match, Market market) {
        registerSubCommand("vegetable", new BuyVegetableCommand(market, match));
        registerSubCommand("land", new BuyLandCommand(market, match));
    }

    @Override
    public String getDefaultErrorMessage() {
        return "Arguments accepted: vegetable, land";
    }

    @Override
    public String getNoArgMessage() {
        return "Arguments accepted: vegetable, land";
    }
}
