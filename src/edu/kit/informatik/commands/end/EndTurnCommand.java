package edu.kit.informatik.commands.end;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;

import java.util.Collections;
import java.util.List;

/**
 * A class to handle the end turn command.
 *
 * @author uswry
 * @version 1.0
 */
public class EndTurnCommand extends Command {

    private final Match match;

    /**
     * Initializing the needed constants.
     *
     * @param match - An instance of the Match class
     */
    public EndTurnCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        match.endRound();
        return Collections.emptyList();
    }
}
