package edu.kit.informatik.game.utility;

import java.util.Scanner;

public class Inputs {

    private static final String INVALID_INPUT = "Error: invalid input";
    public static final String DIGIT_PATTERN = "-?\\d+";
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

    public boolean askInitialInput() {
        int input = askInputNumber(numberPlayersText, 1);
        if (input == -1) return false;
        numberPlayers = input;

        playerNames = new String[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            String playerName = askInput(String.format(playerNamesText, i + 1), STRING_PATTERN, true);
            if (playerName.equals("quit")) return false;
            playerNames[i] = playerName;
        }

        input = askInputNumber(startGoldText, 0);
        if (input == -1) return false;
        startGold = input;

        input = askInputNumber(winGoldText, 1);
        if (input == - 1) return false;
        winGold = input;

        boolean print = true;
        do {
            String textInput = askInput(shuffleSeedText, DIGIT_PATTERN, print);
            if (textInput.equals("quit")) return false;
            try {
                shuffleSeed = Integer.parseInt(textInput);
                break;
            } catch (NumberFormatException ignored) {
                print = false;
                System.out.println(INVALID_INPUT);
            }
        } while (true);

        return true;
    }

    private int askInputNumber(String message, int min) {
        boolean print = true;
        do {
            String input = askInput(message, Inputs.DIGIT_PATTERN, print);
            if (input.equals("quit")) return -1;
            try {
                int inputNumber = Integer.parseInt(input);
                if (min > inputNumber) {
                    print = false;
                    System.out.println(INVALID_INPUT);
                    continue;
                }
                return inputNumber;
            } catch (NumberFormatException ignored) {
                print = false;
                System.out.println(INVALID_INPUT);
            }
        } while (true);

    }

    private String askInput(String message, String regex, boolean print) {
        String input = "";
        if (print) System.out.println(message);
        do {
            input = scanner.nextLine();
            if (input.equals("quit")) return input;
            if (!input.matches(regex)) {
                System.out.println(INVALID_INPUT);
            }
        } while (!input.matches(regex));
        return input;
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
