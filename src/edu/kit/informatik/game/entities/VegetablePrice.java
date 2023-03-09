package edu.kit.informatik.game.entities;

/**
 * This class contains the currents price of a vegetable
 * and its price scala.
 *
 * @author uswry
 * @version 1.0
 */
public class VegetablePrice {

    private final int[] prices;
    private int currentIndex;

    /**
     * Initializes the needed variable and constants.
     *
     * @param prices - The price scala of the vegetable
     * @param currentIndex - The current index of the price scala
     */
    public VegetablePrice(int[] prices, int currentIndex) {
        this.prices = prices;
        this.currentIndex = currentIndex;
    }

    /**
     * Returns the current price
     * @return the current price
     */
    public int getCurrentPrice() {
        return prices[currentIndex];
    }

    /**
     * Increases the price if it's not already ad its maximum.
     */
    public void increasePrice() {
        if (currentIndex < prices.length - 1) {
            currentIndex++;
        }
    }

    /**
     * Decreases the price if it's not already ad its lowest.
     */
    public void decreasePrice() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }
}
