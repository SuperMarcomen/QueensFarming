package edu.kit.informatik.game.utility.printers;

import edu.kit.informatik.game.entities.Vegetable;

/**
 * A class that contains common methods and constants
 * needed for printer that manage vegetables.
 *
 * @author uswry
 * @version 1.0
 */
public abstract class VegetablePrinter implements Printer {

    /**
     * The format to print a vegetable
     */
    protected static final String VEGETABLE_FORMAT = "%s:";
    /**
     * This constant represents the spacing variable.
     */
    protected static final String WORD_SPACING = "%wordSpacing";
    /**
     * This constant represents the number variable.
     */
    protected static final String NUMBER_SPACING = "%numberSpacing";
    /**
     * This constant represents the total spacing variable.
     */
    protected static final String TOTAL_SPACING = "%totalSpacing";
    private final Vegetable[] availableVegetables;

    /**
     * Initializes the needed constant.
     * @param availableVegetables - The vegetables that are available
     */
    public VegetablePrinter(Vegetable[] availableVegetables) {
        this.availableVegetables = availableVegetables;
    }

    /**
     * Replaces the variables in the given string.
     *
     * @param string - The string where the variables will be replaced
     * @return the modified string
     */
    protected String replaceVariables(String string) {
        int wordSpacing = getWordSpacing();
        int numberSpacing = getNumberSpacing();
        return string
                .replace(WORD_SPACING, String.valueOf(wordSpacing))
                .replace(NUMBER_SPACING, String.valueOf(numberSpacing))
                .replace(TOTAL_SPACING, String.valueOf(wordSpacing + numberSpacing + 1));
    }

    /**
     * Gets the maximum length of each word.
     * @return the maximum length of each word
     */
    protected int getWordSpacing() {
        int longestWord = 0;
        for (Vegetable vegetable : availableVegetables) {
            String word = String.format(VEGETABLE_FORMAT, vegetable.getPluralName());
            if (word.length() > longestWord) longestWord = word.length();
        }

        return longestWord;
    }

    /**
     * Returns the maximum length of each number.
     * @return the maximum length of each number
     */
    protected int getNumberSpacing() {
        int biggestNumber = 0;
        for (Vegetable vegetable : Vegetable.values()) {
            if (vegetable.getVegetablePrice().getCurrentPrice() > biggestNumber) {
                biggestNumber = vegetable.getVegetablePrice().getCurrentPrice();
            }
        }

        return (int) Math.floor(Math.log10(biggestNumber)) + 1;
    }
}
