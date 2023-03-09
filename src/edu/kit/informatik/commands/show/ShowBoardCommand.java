package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.utility.printers.BoardPrinter;

import java.util.List;

/**
 * A class to handle the show board command.
 *
 * @author uswry
 * @version 1.0
 */
public class ShowBoardCommand extends Command {

    private final Match match;

    /**
     * Initializing the needed constants.
     *
     * @param match - An instance of the Match class
     */
    public ShowBoardCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        return new BoardPrinter(match.getCurrentPlayer()).print();
    }
}
