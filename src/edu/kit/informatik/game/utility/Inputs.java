package edu.kit.informatik.game.utility;

import java.util.Scanner;

public class Inputs {

    public static final String DIGIT_PATTERN = "\\d+";
    public static final String STRING_PATTERN = "[a-zA-Z]+";
    private int numberPlayers;
    private static final String numberPlayersText = "How many players?"; // TODO upper case
    private String[] playerNames;
    private static final String playerNamesText = "Enter the name of player %d:";
    private int startGold;
    private static final String startGoldText = "With how much gold should each player start?";
    private int winGold;
    private static final String winGoldText = "With how much gold should a player win?";
    private long shuffleSeed;
    private static final String shuffleSeedText = "Please enter the seed used to shuffle the tiles:";
    private final Scanner scanner;

    public Inputs(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean askInitialInput() { // TODO gold win >= 1
        String input = askInput(numberPlayersText, DIGIT_PATTERN);
        if (input.equals("quit")) return false;
        numberPlayers = Integer.parseInt(input);

        playerNames = new String[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            String playerName = askInput(String.format(playerNamesText, i + 1), STRING_PATTERN);
            if (playerName.equals("quit")) return false;
            playerNames[i] = playerName;
        }

        input = askInput(startGoldText, DIGIT_PATTERN);
        if (input.equals("quit")) return false;
        startGold = Integer.parseInt(input);

        input = askInput(winGoldText, DIGIT_PATTERN);
        if (input.equals("quit")) return false;
        winGold = Integer.parseInt(input);

        input = askInput(shuffleSeedText, DIGIT_PATTERN);
        if (input.equals("quit")) return false;
        shuffleSeed = Long.parseLong(input);

        return true;
    }

    private String askInput(String message, String regex) {
        String input = "";
        System.out.println(message);
        do {
            input = scanner.nextLine();
            if (input.equals("quit")) return input;
        } while (!input.matches(regex));
        return input;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public int getStartGold() {
        return startGold;
    }

    public int getWinGold() {
        return winGold;
    }

    public long getShuffleSeed() {
        return shuffleSeed;
    }
}
