package edu.kit.informatik.exceptions;

public class GameException extends IllegalArgumentException {

    public GameException(String errorMessage) {
        super(errorMessage);
    }
}
