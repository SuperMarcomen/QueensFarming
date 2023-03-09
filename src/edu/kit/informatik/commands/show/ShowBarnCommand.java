package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.utility.printers.BarnPrinter;

import java.util.List;

/**
 * A class to handle the show barn command.
 *
 * @author uswry
 * @version 1.0
 */
public class ShowBarnCommand extends Command {

    private final Match match;

    /**
     * Initializing the needed constants.
     *
     * @param match - An instance of the Match class
     */
    public ShowBarnCommand(Match match) {
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        return new BarnPrinter(match.getCurrentPlayer().getBarn(), match.getCurrentPlayer().getGold()).print();
    }
}
