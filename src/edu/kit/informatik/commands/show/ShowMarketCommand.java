package edu.kit.informatik.commands.show;

import edu.kit.informatik.commands.Command;

import java.util.List;

public class ShowMarketCommand extends Command {
    @Override
    protected List<String> execute() {
        return List.of("mammt");
    }
}
