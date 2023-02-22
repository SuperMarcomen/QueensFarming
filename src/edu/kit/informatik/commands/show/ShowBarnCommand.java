package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.utility.BarnPrinter;

import java.util.List;

public class ShowBarnCommand extends Command {

    private final Match match;

    public ShowBarnCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        return new BarnPrinter(match.getCurrentPlayer().getBarn(), match.getCurrentPlayer().getGold()).print();
    }
}
