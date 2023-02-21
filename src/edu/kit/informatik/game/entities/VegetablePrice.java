package edu.kit.informatik.game.entities;

public class VegetablePrice {

    private final int[] prices;
    private int currentIndex;

    public VegetablePrice(int[] prices, int currentIndex) {
        this.prices = prices;
        this.currentIndex = currentIndex;
    }

    public int getCurrentPrice() {
        return prices[currentIndex];
    }

    public void increasePrice() {
        if (currentIndex < prices.length - 1) {
            currentIndex++;
        }
    }

    public void decreasePrice() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }
}
