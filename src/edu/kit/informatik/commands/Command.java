package edu.kit.informatik.commands;

import java.util.List;

public abstract class Command {

    protected String input;

    public List<String> executeDefault() {
        return execute();
    }

    protected abstract List<String> execute();

    public boolean canExecute(String input) {
        this.input = input;
        return input.isEmpty();
    }

    public void setInput(String input) {
        this.input = input;
    }
}
