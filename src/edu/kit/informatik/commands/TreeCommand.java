package edu.kit.informatik.commands;

import edu.kit.informatik.exceptions.GameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract  class TreeCommand extends Command {

    protected final HashMap<String, Command> commands = new HashMap<>();
    protected String input;

    @Override
    public List<String> executeDefault() {
        Command command = getCommandToExecute(input);
        if (command == this) {
            return execute();
        } else {
            return command.executeDefault();
        }
    }

    @Override
    public List<String> execute() {
        List<String> arguments = new ArrayList<>(Arrays.stream(input.split(" ")).toList());
        char lastChar = input.charAt(input.length() - 1);
        if (Character.isSpaceChar(lastChar)) arguments.add(String.valueOf(lastChar));

        Command command = getCommandToExecute(arguments.get(0));
        String truncatedInput = input.length() > arguments.get(0).length() ? input.substring(arguments.get(0).length() + 1) : "";
        command.setInput(truncatedInput);
        return command.execute();
    }

    @Override
    public boolean canExecute(String input) {
        this.input = input;
        List<String> arguments = new ArrayList<>(Arrays.stream(input.split(" ")).toList());
        if (input.isBlank()) {
            throw new GameException(getNoArgMessage());
        }
        char lastChar = input.charAt(input.length() - 1);
        if (Character.isSpaceChar(lastChar)) arguments.add(String.valueOf(lastChar));

        Command command = getCommandToExecute(arguments.get(0));
        if (command == this) {
            throw new GameException(getDefaultErrorMessage());
        } else {
            String truncatedInput = input.length() > arguments.get(0).length() ? input.substring(arguments.get(0).length() + 1) : "";
            if (truncatedInput.isEmpty() && Character.isSpaceChar(lastChar)) {
                truncatedInput = String.valueOf(lastChar);
            }
            return command.canExecute(truncatedInput);
        }
    }

    @Override
    public String getCorrectFormat() {
        return getCommandToExecute(input.split(" ")[0]).getCorrectFormat();
    }

    public abstract String getDefaultErrorMessage();

    public abstract String getNoArgMessage();

    protected Command getCommandToExecute(String argument) {
        if (!commands.isEmpty()) {
            Command command = commands.get(argument);
            if (command != null) return command;
        }
        return this;
    }

    public void registerSubCommand(String name, Command command) {
        commands.put(name, command);
    }
}
