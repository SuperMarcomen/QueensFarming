package edu.kit.informatik.commands;

public abstract class InputCommand extends Command {

    private static final String INPUT_ERROR_MESSAGE = "Error: This command requires these parameters: %s";
    private final String pattern;
    private final String correctFormat;

    /**
     * Initializing the regex pattern
     *
     * @param pattern           - the regex pattern for the input
     * @param correctFormat
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
