package edu.kit.informatik.commands;

import edu.kit.informatik.exceptions.GameException;

/**
 * A class to handle commands with input.
 *
 * @author uswry
 * @version 1.0
 */
public abstract class InputCommand extends Command {

    private static final String INPUT_ERROR_MESSAGE = "Error: This command requires these parameters: %s";
    private static final String NUMBER_NOT_ACCEPTED_RANGE = "The number you wrote is not in the accepted range!";
    private final String pattern;
    private final String correctFormat;

    /**
     * Initializing the regex pattern and the correct format of the input.
     *
     * @param pattern - The regex pattern for the input
     * @param correctFormat - The correct format of the input
     */
    public InputCommand(String pattern, String correctFormat) {
        this.pattern = pattern;
        this.correctFormat = correctFormat;
    }

    @Override
    public boolean canExecute(String input) {
        return input.matches(pattern);
    }

    @Override
    public String getCorrectFormat() {
        return String.format(INPUT_ERROR_MESSAGE, correctFormat);
    }

    /**
     * Parses an integer from a string, checking if an exception is thrown.
     *
     * @param input - The string input
     * @return the integer parsed from the string
     * @throws GameException if the number exceeds the Integer limit
     */
    public int parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new GameException(NUMBER_NOT_ACCEPTED_RANGE);
        }
    }
}
