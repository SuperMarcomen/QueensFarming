package edu.kit.informatik.commands;

import edu.kit.informatik.game.Match;

import java.util.List;

/**
 * A class to handle the quit command.
 *
 * @author uswry
 * @version 1.0
 */
public class QuitCommand extends Command {

    private final Match match;

    /**
     * Initializing the needed constants.
     *
     * @param match - An instance of the Match class
     */
    public QuitCommand(Match match) {
        this.match = match;
    }


    @Override
    protected List<String> execute() {
        return match.endMatch();
    }
}
