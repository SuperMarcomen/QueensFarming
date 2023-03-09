package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Vegetable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Prints the market for the console.
 *
 * @author uswry
 * @version 1.0
 */
public class MarketPrinter extends VegetablePrinter {

    private static final String VEGETABLE_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";

    /**
     * Calls the super constructor with the values of the vegetables.
     */
    public MarketPrinter() {
        super(Vegetable.values());
    }

    @Override
    public List<String> print() {
        List<String> strings = new ArrayList<>();
        Map<Vegetable, Integer> vegetables = new LinkedHashMap<>();
        for (Vegetable vegetable : Vegetable.values()) {
            vegetables.put(vegetable, vegetable.getVegetablePrice().getCurrentPrice());
        }

        for (Map.Entry<Vegetable, Integer> vegetable : vegetables.entrySet()) {
            String string = String.format(replaceVariables(VEGETABLE_LINE),
                    vegetable.getKey().getPluralName() + ':',
                    vegetable.getValue());
            strings.add(string);
        }
        return strings;
    }

}
