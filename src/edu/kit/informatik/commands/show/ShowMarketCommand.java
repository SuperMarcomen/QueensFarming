package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.utility.printers.MarketPrinter;

import java.util.List;

public class ShowMarketCommand extends Command {

    private final Market market;

    public ShowMarketCommand(Market market) {
        this.market = market;
    }

    @Override
    protected List<String> execute() {
        return new MarketPrinter(market).print();
    }
}
