package edu.kit.informatik.commands;

public class Commands extends TreeCommand {

    @Override
    public String getDefaultErrorMessage() {
        return "Command not recognized";
    }

    @Override
    public String getNoArgMessage() {
        return "Command not recognized";
    }


}
