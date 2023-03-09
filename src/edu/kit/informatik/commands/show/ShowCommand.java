package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Match;

/**
 * A class to handle the show command.
 *
 * @author uswry
 * @version 1.0
 */
public class ShowCommand extends TreeCommand {

    /**
     * Initializing the needed constants and registering the subcommands.
     *
     * @param match - An instance of the Match class
     */
    public ShowCommand(Match match) {
        registerSubCommand("market", new ShowMarketCommand());
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
