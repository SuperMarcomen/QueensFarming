package edu.kit.informatik.commands.end;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Match;

/**
 * A class to handle the end command.
 *
 * @author uswry
 * @version 1.0
 */
public class EndCommand extends TreeCommand {

    /**
     * Registering the turn subcommand.
     *
     * @param match - An instance of the Match class
     */
    public EndCommand(Match match) {
        registerSubCommand("turn", new EndTurnCommand(match));
    }


    @Override
    public String getDefaultErrorMessage() {
        return "This command needs another argument to work";
    }

    @Override
    public String getNoArgMessage() {
        return "Arguments accepted: barn, market, idk";
    }
}
