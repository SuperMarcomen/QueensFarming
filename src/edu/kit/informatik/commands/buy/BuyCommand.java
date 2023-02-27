package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;

public class BuyCommand extends TreeCommand {


    public BuyCommand(Match match, Market market) {
        registerSubCommand("vegetable", new BuyVegetableCommand(market, match));
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
