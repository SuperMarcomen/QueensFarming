package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Vegetable;

public class VegetablePrinter {

    protected static final String VEGETABLE_FORMAT = "%s:";
    protected static final String WORD_SPACING = "%wordSpacing";
    protected static final String NUMBER_SPACING = "%numberSpacing";
    protected static final String TOTAL_SPACING = "%totalSpacing";
    private final Vegetable[] availableVegetables;

    public VegetablePrinter(Vegetable[] availableVegetables) {
        this.availableVegetables = availableVegetables;
    }

    protected String replaceVariables(String string) {
        int wordSpacing = getWordSpacing();
        int numberSpacing = getNumberSpacing();
        return string
                .replace(WORD_SPACING, String.valueOf(wordSpacing))
                .replace(NUMBER_SPACING, String.valueOf(numberSpacing))
                .replace(TOTAL_SPACING, String.valueOf(wordSpacing + numberSpacing + 1));
    }

    protected int getWordSpacing() {
        int longestWord = 0;
        for (Vegetable vegetable : availableVegetables) {
            String word = String.format(VEGETABLE_FORMAT, vegetable.getPluralName());
            if (word.length() > longestWord) longestWord = word.length();
        }

        return longestWord;
    }

    protected int getNumberSpacing() {
        int biggestNumber = 0;
        for (Vegetable vegetable : Vegetable.values()) {
            if (vegetable.getVegetablePrice().getCurrentPrice() > biggestNumber) biggestNumber = vegetable.getVegetablePrice().getCurrentPrice();
        }

        return (int) Math.floor(Math.log10(biggestNumber)) + 1;
    }
}
