package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BarnPrinter extends VegetablePrinter {

    private static final String BARN_TITLE = "Barn (spoils in %d turns)";
    private static final String VEGETABLE_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SPACER_LINE = "%-" + TOTAL_SPACING + "s";
    private static final char SPACER = '-';
    private static final String SUM_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SUM_TEXT = "Sum:";
    private static final String GOLD_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String GOLD_TEXT = "Gold:";
    private final Barn barn;
    private final int gold;

    public BarnPrinter(Barn barn, int gold) {
        this.barn = barn;
        this.gold = gold;
    }
    public List<String> print() {
        List<String> strings = new ArrayList<>();
        strings.add(String.format(BARN_TITLE, barn.getIntCountdown()));
        if (barn.getTotalVegetables() == 0) return strings;

        int sum = 0;
        Map<Vegetable, Integer> sortedVegetables = barn.getSortedVegetables();

        for (Vegetable vegetable : sortedVegetables.keySet()) {
            int vegetableQuantity = sortedVegetables.get(vegetable);
            if (vegetableQuantity == 0) continue;
            strings.add(String.format(replaceVariables(VEGETABLE_LINE), vegetable.getPluralName() + ':', vegetableQuantity));

            sum += vegetableQuantity;
        }

        strings.add(String.format(replaceVariables(SPACER_LINE), "").replace(" ", String.valueOf(SPACER)));
        strings.add(String.format(replaceVariables(SUM_LINE), SUM_TEXT, sum));

        strings.add("");
        strings.add(String.format(replaceVariables(GOLD_LINE), GOLD_TEXT, gold));

        return strings;
    }

    @Override
    protected int getNumberSpacing() {
        int biggestNumber = gold;
        for (Integer value : barn.getVegetables().values()) {
            if (value > biggestNumber) biggestNumber = value;
        }

        return (int) Math.floor(Math.log10(biggestNumber)) + 1;
    }
}
