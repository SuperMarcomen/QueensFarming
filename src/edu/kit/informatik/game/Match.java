package edu.kit.informatik.game;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Player;

import java.util.*;

public class Match {

    private static final String NEW_ROUND_MESSAGE = "It is %s's turn!";
    private static final String VEGETABLE_HAS_GROWN = "%d vegetable has grown since your last turn.";
    private static final String VEGETABLES_HAVE_GROWN = "%d vegetables have grown since your last turn.";
    private static final String SPOILED_VEGETABLES = "The vegetables in your barn are spoiled.";
    private static final String WINNER_PLAYER_LIST_MESSAGE = "Player %d (%s): %d";
    private static final String ONE_WINNER = "%s has won!";
    private static final String TWO_WINNERS = "%s and %s have won!";
    private static final String MULTIPLE_WINNERS = "%s have won!";
    private static final String PLAYER_FORMAT = "%s, ";
    private static final String SECOND_TO_LAST_PLAYER_FORMAT = "%s ";
    private static final String LAST_PLAYER_FORMAT = "and %s";
    private final Player[] players;
    private final int winGold;
    private final Market market;
    private int indexOfPlayer;
    private boolean newRound;
    private boolean newRoundSet;
    private boolean ended;
    private int actionsLeft;

    public Match(Player[] players, Market market, int winGold) {
        this.players = players;
        this.winGold = winGold;
        this.market = market;
        indexOfPlayer = 0;
        newRound = true;
        newRoundSet = false;
        ended = false;
    }

    public List<String> handleRound() {
        if (!newRound) return Collections.emptyList();
        List<String> output = new ArrayList<>();

        if (newRoundSet) {
            List<Player> winners = getWinners();
            if (winners.size() > 0) {
                return endMatch();
            } else {
                newRoundSet = false;
            }
        }

        actionsLeft = 2;
        newRound = false;
        market.adjustPrices();
        market.reset();
        Player player = getCurrentPlayer();
        output.add("");
        output.add(String.format(NEW_ROUND_MESSAGE, player.getName()));
        int grownVegetable = player.getNumberGrownVegetables();
        if (grownVegetable == 1) {
            output.add(String.format(VEGETABLE_HAS_GROWN, grownVegetable));
        } else if (grownVegetable > 1) {
            output.add(String.format(VEGETABLES_HAVE_GROWN, grownVegetable));
        }
        Barn barn = player.getBarn();
        if (barn.haveVegetablesSpoiled()) {
            output.add(SPOILED_VEGETABLES);
            barn.setVegetablesSpoiled(false);
        }
        return output;
    }

    public List<String> endMatch() {
        return endMatch(getWinners());
    }

    private List<String> endMatch(List<Player> winners) {
        // TODO what happens if game is quitted manually and no one wins?
        List<String> output = new ArrayList<>();
        for (Player player : players) {
            output.add(String.format(WINNER_PLAYER_LIST_MESSAGE, player.getId() + 1, player.getName(), player.getGold()));
        }
        if (winners.size() > 0) {
            output.add(getWinnerMessage(winners));
        } else {
            Arrays.sort(players);
            List<Player> richestPlayers = new ArrayList<>();
            int goldAmount = players[0].getGold();
            for (Player player : players) {
                if (player.getGold() == goldAmount) {
                    richestPlayers.add(player);
                }
            }
            output.add(getWinnerMessage(richestPlayers));
        }

        ended = true;
        return output;
    }

    private String getWinnerMessage(List<Player> winners) {
        if (winners.size() == 1) {
            return String.format(ONE_WINNER, winners.get(0).getName());
        } else if (winners.size() == 2) {
            return String.format(TWO_WINNERS, winners.get(0).getName(), winners.get(1).getName());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < winners.size(); i++) {
            if (i == winners.size() - 1) {
                stringBuilder.append(String.format(LAST_PLAYER_FORMAT, winners.get(i).getName()));
                continue;
            } else if (i == winners.size() - 2) {
                stringBuilder.append(String.format(SECOND_TO_LAST_PLAYER_FORMAT, winners.get(i).getName()));
                continue;
            }
            stringBuilder.append(String.format(PLAYER_FORMAT, winners.get(i).getName()));
        }

        return String.format(MULTIPLE_WINNERS, stringBuilder);
    }

    public void endRound() {
        nextPlayer();
        newRound = true;
    }

    private List<Player> getWinners() {
        List<Player> winners = new LinkedList<>();
        for (Player player : players) {
            if (player.getGold() >= winGold) {
                winners.add(player);
            }
        }
        return winners;
    }

    private void nextPlayer() {
        Player player = getCurrentPlayer();
        player.growFields();
        Barn barn = player.getBarn();
        barn.reduceCountdown();
        if (barn.getTotalVegetables() > 0 && barn.getIntCountdown() == 0) {
            barn.spoilVegetables();
        }
        indexOfPlayer++;
        if (indexOfPlayer >= players.length) {
            indexOfPlayer = 0;
            newRoundSet = true;
        }
    }

    public Player getCurrentPlayer() {
        return players[indexOfPlayer];
    }

    public void reduceActions() {
        if (actionsLeft <= 0) return;
        actionsLeft--;
        if (actionsLeft == 0) {
            newRound = true;
            nextPlayer();
        }
    }

    public boolean hasEnded() {
        return ended;
    }
}
