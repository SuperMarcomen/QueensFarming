package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Vegetable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarketPrinter extends VegetablePrinter {

    private static final String VEGETABLE_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";

    public MarketPrinter() {
        super(Vegetable.values());
    }

    public List<String> print() {
        List<String> strings = new ArrayList<>();
        Map<Vegetable, Integer> vegetables = new LinkedHashMap<>();
        for (Vegetable vegetable : Vegetable.values()) {
            vegetables.put(vegetable, vegetable.getVegetablePrice().getCurrentPrice());
        }

        for (Map.Entry<Vegetable, Integer> vegetable : vegetables.entrySet()) {
            strings.add(String.format(replaceVariables(VEGETABLE_LINE), vegetable.getKey().getPluralName() + ':', vegetable.getValue()));
        }
        return strings;
    }

}
