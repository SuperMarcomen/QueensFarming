package edu.kit.informatik;

import edu.kit.informatik.game.utility.Inputs;

import java.util.List;
import java.util.Scanner;

public class GameStarter {

    private static final String PIXEL_ART = """
                                           _.-^-._    .--.
                                        .-'   _   '-. |__|
                                       /     |_|     \\|  |
                                      /               \\  |
                                     /|     _____     |\\ |
                                      |    |==|==|    |  |
                  |---|---|---|---|---|    |--|--|    |  |
                  |---|---|---|---|---|    |==|==|    |  |
                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                ^^^^^^^^^^^^^^^ QUEENS FARMING ^^^^^^^^^^^^^^^
                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                """;

    public static void main(String[] args) {
        // TODO check args
        System.out.println(PIXEL_ART);
        Scanner scanner = new Scanner(System.in);
        Inputs inputs = new Inputs(scanner);
        boolean isCorrect = inputs.askInitialInput();
        if (!isCorrect) return;

        QueensFarming queensFarming = new QueensFarming(inputs);
        queensFarming.init().forEach(System.out::println);
        do {
            String input = scanner.nextLine();
            List<String> output = queensFarming.handleInput(input);
            if (output != null) {
                output.forEach(System.out::println);
            }
        } while (queensFarming.isRunning());
        scanner.close();
    }
}
