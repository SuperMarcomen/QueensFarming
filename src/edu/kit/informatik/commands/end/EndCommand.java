package edu.kit.informatik.commands.end;

import edu.kit.informatik.commands.TreeCommand;
import edu.kit.informatik.game.Match;

public class EndCommand extends TreeCommand {

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
