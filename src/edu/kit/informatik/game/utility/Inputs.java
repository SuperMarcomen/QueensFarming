package edu.kit.informatik.game.utility;

import java.util.Scanner;

/**
 * This class asks the user for input from the console
 * and checks if they are correct.
 *
 * @author uswry
 * @version 1.0
 */
public class Inputs {

    private static final String INVALID_INPUT = "Error: invalid input";
    private static final String DIGIT_PATTERN = "-?\\d+";
    private static final String STRING_PATTERN = "[a-zA-Z]+";
    private static final String NUMBER_PLAYERS_TEXT = "How many players?"; // TODO upper case
    private static final String PLAYER_NAMES_TEXT = "Enter the name of player %d:";
    private static final String START_GOLD_TEXT = "With how much gold should each player start?";
    private static final String WIN_GOLD_TEXT = "With how much gold should a player win?";
    private static final String SHUFFLE_SEED_TEXT = "Please enter the seed used to shuffle the tiles:";
    private final Scanner scanner;
    private String[] playerNames;
    private int startGold;
    private int winGold;
    private int shuffleSeed;

    /**
     * Initializes the value of the scanner.
     * @param scanner - An instance of the scanner
     */
    public Inputs(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Asks the user for the inputs needed to run the game and
     * returns false if the player wants to quit.
     *
     * @return false if the player wants to quit, true otherwise
     */
    public boolean askInitialInput() {
        int input = askInputNumber(NUMBER_PLAYERS_TEXT, 1);
        if (input == -1) return false;
        int numberPlayers = input;

        playerNames = new String[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            String playerName = askInput(String.format(PLAYER_NAMES_TEXT, i + 1), STRING_PATTERN, true);
            if (playerName.equals("quit")) return false;
            playerNames[i] = playerName;
        }

        input = askInputNumber(START_GOLD_TEXT, 0);
        if (input == -1) return false;
        startGold = input;

        input = askInputNumber(WIN_GOLD_TEXT, 1);
        if (input == -1) return false;
        winGold = input;

        boolean print = true;
        do {
            String textInput = askInput(SHUFFLE_SEED_TEXT, DIGIT_PATTERN, print);
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

    /**
     * Returns the player name.
     * @return the player name
     */
    public String[] getPlayerNames() {
        return playerNames;
    }

    /**
     * Returns the start gold.
     * @return the start gold
     */
    public int getStartGold() {
        return startGold;
    }

    /**
     * Returns the amount of gold to win the match.
     * @return the amount of gold to win the match
     */
    public int getWinGold() {
        return winGold;
    }

    /**
     * Returns the shuffle seed.
     * @return the shuffle seed
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }
}
