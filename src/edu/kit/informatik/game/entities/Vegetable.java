package edu.kit.informatik.game.entities;

/**
 * It represents the different types of vegetables available
 * and stores its properties.
 *
 * @author uswry
 * @version 1.0
 */
public enum Vegetable {

    /**
     * The mushroom vegetable.
     */
    MUSHROOM('M', "mushroom", "mushrooms", 4, new VegetablePrice(new int[]{12, 15, 16, 17, 20}, 2)),
    /**
     * The carrot vegetable
     */
    CARROT('C', "carrot", "carrots", 1, new VegetablePrice(new int[]{1, 2, 2, 2, 3}, 2)),
    /**
     * The tomato vegetable.
     */
    TOMATO('T', "tomato", "tomatoes", 3, new VegetablePrice(new int[]{3, 5, 6, 7, 9}, 2)),
    /**
     * The salad vegetable.
     */
    SALAD('S', "salad", "salads", 2, new VegetablePrice(new int[]{2, 3, 4, 5, 6}, 2));

    private final char symbol;
    private final String singularName;
    private final String pluralName;
    private final int growthTime;
    private final VegetablePrice vegetablePrice;

    /**
     * Initializes all the properties of the vegetable.
     *
     * @param symbol - The symbol that represents the vegetable
     * @param singularName - The singular name of the vegetable
     * @param pluralName - The plural name of the vegetable
     * @param growthTime - The time the vegetable needs to grow
     * @param vegetablePrice - The price of the vegetable
     */
    Vegetable(char symbol, String singularName, String pluralName, int growthTime, VegetablePrice vegetablePrice) {
        this.symbol = symbol;
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.growthTime = growthTime;
        this.vegetablePrice = vegetablePrice;
    }

    /**
     * Returns the Vegetable represented by the given String.
     * @param text - The String representing a Vegetable
     * @return the Vegetable represented by the given String
     */
    public static Vegetable fromString(String text) {
        for (Vegetable vegetable : Vegetable.values()) {
            if (vegetable.getSingularName().equals(text)) {
                return vegetable;
            }
        }
        return null;
    }

    /**
     * Returns the singular or plural name based on the quantity.
     * @param quantity - The quantity of the vegetable
     * @return the singular or plural name based on the quantity
     */
    public String getNameFromQuantity(int quantity) {
        if (quantity == 1) {
            return singularName;
        } else {
            return pluralName;
        }
    }

    /**
     * Returns the symbol.
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Return the singular name.
     * @return the singular name
     */
    public String getSingularName() {
        return singularName;
    }

    /**
     * Returns the plural name.
     * @return the plural name
     */
    public String getPluralName() {
        return pluralName;
    }

    /**
     * Returns the growth time.
     * @return the growth time
     */
    public int getGrowthTime() {
        return growthTime;
    }

    /**
     * Returns the vegetable price.
     * @return the vegetable price
     */
    public VegetablePrice getVegetablePrice() {
        return vegetablePrice;
    }
}
