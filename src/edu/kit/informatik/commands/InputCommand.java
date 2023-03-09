package edu.kit.informatik.commands;

/**
 * A class to handle commands with input.
 *
 * @author uswry
 * @version 1.0
 */
public abstract class InputCommand extends Command {

    private static final String INPUT_ERROR_MESSAGE = "Error: This command requires these parameters: %s";
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
}
