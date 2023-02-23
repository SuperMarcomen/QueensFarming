package edu.kit.informatik.commands.end;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;

import java.util.Collections;
import java.util.List;

public class EndTurnCommand extends Command {

    private final Match match;

    public EndTurnCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        match.endRound();
        return Collections.emptyList();
    }
}
