package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;

public class ShowCommand extends TreeCommand {

    public ShowCommand(Market market, Match match) {
        registerSubCommand("market", new ShowMarketCommand(market));
        registerSubCommand("barn", new ShowBarnCommand(match));
        registerSubCommand("board", new ShowBoardCommand(match));
    }

    @Override
    public String getDefaultErrorMessage() {
        return "This command needs another argument to work";
    }

    @Override
    public String getNoArgMessage() {
        return "Arguments accepted: barn, market, board";
    }


}
