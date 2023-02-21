package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;

import java.util.List;

public class ShowBarnCommand extends Command {

    private final Match match;

    public ShowBarnCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        List<String> lines = match.getCurrentPlayer().printBarn();
        lines.add("");
        lines.add(String.format("%-12s %d", "Gold:", match.getCurrentPlayer().getGold()));
        return lines;
    }
}
