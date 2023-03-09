package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.utility.printers.MarketPrinter;

import java.util.List;

/**
 * A class to handle the show market command.
 *
 * @author uswry
 * @version 1.0
 */
public class ShowMarketCommand extends Command {

    @Override
    protected List<String> execute() {
        return new MarketPrinter().print();
    }
}
