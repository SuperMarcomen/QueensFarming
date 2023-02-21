package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.utility.FieldPrinter;
import edu.kit.informatik.game.Match;

import java.util.List;

public class ShowBoardCommand extends Command {

    private final Match match;

    public ShowBoardCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        return new FieldPrinter(match.getCurrentPlayer()).print();
    }
}
