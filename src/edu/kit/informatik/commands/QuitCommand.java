package edu.kit.informatik.commands;

import edu.kit.informatik.game.Match;

import java.util.List;

public class QuitCommand extends Command {

    private final Match match;

    public QuitCommand(Match match) {
        this.match = match;
    }


    @Override
    protected List<String> execute() {
        return match.endMatch();
    }
}
