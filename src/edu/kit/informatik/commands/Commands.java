package edu.kit.informatik.commands;

public class Commands extends TreeCommand {

    @Override
    public String getDefaultErrorMessage() {
        return "Error: Command not recognized";
    }

    @Override
    public String getNoArgMessage() {
        return "Error: Command not recognized";
    }


}
