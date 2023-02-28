package edu.kit.informatik.commands.buy;

import edu.kit.informatik.commands.InputCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;

import java.util.List;

public class BuyLandCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "-?\\d+ \\d+";
    private static final String CORRECT_FORMAT = "buy land [x-coordinate] [y-coordinate]";
    private static final String NOT_ENOUGH_MONEY = "Error: You don't have enough money!";
    private static final String NOT_REACHABLE = "Error: You can't reach this field!";
    private static final String ALREADY_OCCUPIED = "Error: This field is already occupied!";
    private final Match match;
    private final Market market;
    /**
     * Initializing the regex pattern
     *
     * @param market
     * @param match
     */
    public BuyLandCommand(Market market, Match match) {
        super(ARGUMENT_REGEX, CORRECT_FORMAT);
        this.market = market;
        this.match = match;
    }

    @Override
    protected List<String> execute() {
        Player player = match.getCurrentPlayer();
        String[] args = input.split(" ");
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        if (player.isFieldAvailable(x, y)) return List.of(ALREADY_OCCUPIED);
        if (!player.canReachField(x, y)) return List.of(NOT_REACHABLE);
        if (!player.hasEnoughMoney(market.getFieldPrice(x, y))) return List.of(NOT_ENOUGH_MONEY);
        match.reduceActions();

        return List.of(market.buyField(player, x, y));
    }
}
