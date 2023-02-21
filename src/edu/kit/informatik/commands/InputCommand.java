package edu.kit.informatik.commands;

public abstract class InputCommand extends Command {

    private final String pattern;

    /**
     * Initializing the regex pattern
     * @param pattern - the regex pattern for the input
     */
    public InputCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean canExecute(String input) {
        return input.matches(pattern);
    }
}
