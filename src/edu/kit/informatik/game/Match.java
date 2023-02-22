package edu.kit.informatik.game;

import edu.kit.informatik.game.entities.*;

import java.util.*;

public class Match {

    private final Player[] players;
    private final int winGold;
    private final Market market;
    private final List<Field> availableFields;
    private final Map<Vegetable, Integer> vegetablesPrices;
    private final String NEW_ROUND_MESSAGE = "It's %s's turn!";
    private int indexOfPlayer;
    private boolean newRound;
    private int actionsLeft;


    public Match(Player[] players, int winGold, long shuffleSeed) {
        this.players = players;
        this.winGold = winGold;
        market = new Market();
        indexOfPlayer = 0;
        newRound = true;
        availableFields = generateFields(players.length, shuffleSeed);
        vegetablesPrices = new HashMap<>();
        vegetablesPrices.put(Vegetable.MUSHROOM, 16);
        vegetablesPrices.put(Vegetable.CARROT, 2);
        vegetablesPrices.put(Vegetable.TOMATO, 6);
        vegetablesPrices.put(Vegetable.SALAD, 4);
    }

    public List<String> handleRound() {
        List<String> output = new ArrayList<>();
        if (newRound) {
            actionsLeft = 2;
            newRound = false;
            market.adjustPrices();
            market.reset();
            output.add("");
            output.add(String.format(NEW_ROUND_MESSAGE, getCurrentPlayer().getName()));
        }
        return output;
    }

    private void nextPlayer() {
        getCurrentPlayer().growFields();
        indexOfPlayer++;
        if (indexOfPlayer >= players.length) indexOfPlayer = 0;
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

    private List<Field> generateFields(int numberPlayers, long shuffleSeed) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < numberPlayers; i++) {
            for (int j = 0; j < 4; j++) {
                fields.add(new Field(Tiles.GARDEN));
                fields.add(new Field(Tiles.FIELD));
            }
            for (int j = 0; j < 2; j++) {
                fields.add(new Field(Tiles.LARGE_FIELD));
                fields.add(new Field(Tiles.FOREST));
            }
            fields.add(new Field(Tiles.LARGE_FOREST));
        }

        Collections.shuffle(fields, new Random(shuffleSeed));
        return fields;
    }
}
