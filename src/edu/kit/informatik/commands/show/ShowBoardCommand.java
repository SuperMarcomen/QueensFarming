package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.utility.printers.BoardPrinter;
import edu.kit.informatik.game.Match;

import java.util.List;

public class ShowBoardCommand extends Command {

    private final Match match;

    public ShowBoardCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        return new BoardPrinter(match.getCurrentPlayer()).print();
    }
}
