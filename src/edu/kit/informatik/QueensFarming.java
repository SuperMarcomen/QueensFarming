package edu.kit.informatik;

import edu.kit.informatik.commands.Commands;
import edu.kit.informatik.commands.SellCommand;
import edu.kit.informatik.commands.show.ShowCommand;
import edu.kit.informatik.game.Market;
import edu.kit.informatik.game.Match;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.exceptions.GameException;
import edu.kit.informatik.game.utility.Inputs;

import java.util.ArrayList;
import java.util.List;

public class QueensFarming {

    private final Match match;
    private final Market market;
    private boolean running;
    private final Commands commands;

    public QueensFarming(Inputs input) {
        Player[] players = new Player[input.getPlayerNames().length];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(input.getPlayerNames()[i], input.getStartGold());
        }
        match = new Match(players, input.getWinGold(), input.getShuffleSeed());
        market = new Market();
        running = true;
        commands = new Commands();
        commands.registerSubCommand("show", new ShowCommand(match));
        commands.registerSubCommand("sell", new SellCommand(match, market));
    }

    public List<String> init() {
        return match.handleRound();
    }

    public List<String> handleInput(String input) {
        try {
            if (!commands.canExecute(input)) {
                return List.of("Cannot execute");
            } else {
                List<String> output = new ArrayList<>();
                output.addAll(commands.execute());

                List<String> roundOutput = match.handleRound();
                if (!roundOutput.isEmpty()) output.addAll(roundOutput);
                return output;
            }
        } catch (GameException exception) {
            return List.of(exception.getMessage());
        }
    }

    public boolean isRunning() {
        return running;
    }
}
