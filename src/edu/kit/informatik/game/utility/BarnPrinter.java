package edu.kit.informatik.game.utility;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BarnPrinter {

    private static final String BARN_TITLE = "Barn (spoils in %d turns)";
    private static final String VEGETABLE_FORMAT = "%s:";
    private static final String WORD_SPACING = "%wordSpacing";
    private static final String NUMBER_SPACING = "%numberSpacing";
    private static final String TOTAL_SPACING = "%totalSpacing";
    private static final String VEGETABLE_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SPACER_LINE = "%-" + TOTAL_SPACING + "s";
    private static final char SPACER = '-';
    private static final String SUM_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String SUM_TEXT = "Sum:";
    private static final String GOLD_LINE = "%-" + WORD_SPACING + "s %" + NUMBER_SPACING + "d";
    private static final String GOLD_TEXT = "Gold:";
    private final Barn barn;
    private final int gold;
    private final int wordSpacing;
    private final int numberSpacing;


    public BarnPrinter(Barn barn, int gold) {
        this.barn = barn;
        this.gold = gold;
        wordSpacing = getWordSpacing();
        numberSpacing = getNumberSpacing();
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

    private String replaceVariables(String string) {
        return string
                .replace(WORD_SPACING, String.valueOf(wordSpacing))
                .replace(NUMBER_SPACING, String.valueOf(numberSpacing))
                .replace(TOTAL_SPACING, String.valueOf(wordSpacing + numberSpacing + 1));
    }

    private int getWordSpacing() {
        int longestWord = 0;
        for (Vegetable vegetable : Vegetable.values()) {
            String word = String.format(VEGETABLE_FORMAT, vegetable.getPluralName());
            if (word.length() > longestWord) longestWord = word.length();
        }

        int biggestNumber = gold;
        for (Integer value : barn.getVegetables().values()) {
            if (value > biggestNumber) biggestNumber = value;
        }
        return longestWord;
    }

    private int getNumberSpacing() {
        int biggestNumber = gold;
        for (Integer value : barn.getVegetables().values()) {
            if (value > biggestNumber) biggestNumber = value;
        }

        return (int) Math.floor(Math.log10(biggestNumber)) + 1;
    }
}
