package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Prints the barn for the console.
 *
 * @author uswry
 * @version 1.0
 */
public class BarnPrinter extends VegetablePrinter {

    private static final String BARN = "Barn";
    private static final String BARN_TITLE = BARN + " (spoils in %d turn%s)";
    private static final String VEGETABLE_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SPACER_LINE = "%-" + TOTAL_SPACING + "s";
    private static final char SPACER = '-';
    private static final String SUM_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SUM_TEXT = "Sum:";
    private static final String GOLD_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String GOLD_TEXT = "Gold:";
    private static final String GOLD_TEXT_EMPTY_BARN = "Gold: %d";
    private final Barn barn;
    private final int gold;

    /**
     * Initializes the needed constants and variables.
     *
     * @param barn - An instance of the Barn
     * @param gold - The amount of gold the player has
     */
    public BarnPrinter(Barn barn, int gold) {
        super(barn.getAvailableVegetables());
        this.barn = barn;
        this.gold = gold;
    }

    @Override
    public List<String> print() {
        List<String> strings = new ArrayList<>();
        if (barn.getTotalVegetables() == 0) {
            strings.add(BARN);
            strings.add(String.format(GOLD_TEXT_EMPTY_BARN, gold));
            return strings;
        }
        String plural = barn.getIntCountdown() == 1 ? "" : "s";
        strings.add(String.format(BARN_TITLE, barn.getIntCountdown(), plural));

        int sum = 0;
        Map<Vegetable, Integer> sortedVegetables = barn.getSortedVegetables();

        for (Vegetable vegetable : sortedVegetables.keySet()) {
            int vegetableQuantity = sortedVegetables.get(vegetable);
            if (vegetableQuantity == 0) continue;
            String vegetableLine = String.format(replaceVariables(VEGETABLE_LINE),
                    vegetable.getPluralName() + ':',
                    vegetableQuantity);
            strings.add(vegetableLine);

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
