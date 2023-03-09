package edu.kit.informatik.game.utility.printers;

import java.util.List;

/**
 * Defines the basic properties of a printer.
 *
 * @author uswry
 * @version 1.0
 */
public interface Printer {

    /**
     * Formats the information and returns a list of strings.
     *
     * @return a list of strings containing the wanted information
     *          in a readable format
     */
    List<String> print();
}
