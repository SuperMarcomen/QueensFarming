package edu.kit.informatik.game.entities;

public enum Vegetable {

    MUSHROOM('M', "mushroom", "mushrooms", 4, new VegetablePrice(new int[]{12, 15, 16, 17, 20}, 2)),
    CARROT('C', "carrot", "carrots", 1, new VegetablePrice(new int[]{1, 2, 2, 2, 3}, 2)),
    TOMATO('T', "tomato", "tomatoes", 3, new VegetablePrice(new int[]{3, 5, 6, 7, 9}, 2)),
    SALAD('S', "salad", "salads", 2, new VegetablePrice(new int[]{2, 3, 4, 5, 6}, 2));

    private final char symbol;
    private final String singularName;
    private final String pluralName;
    private final int growthTime;
    private final VegetablePrice vegetablePrice;

    Vegetable(char symbol, String singularName, String pluralName, int growthTime, VegetablePrice vegetablePrice) {
        this.symbol = symbol;
        this.singularName = singularName;
        this.pluralName = pluralName;
        this.growthTime = growthTime;
        this.vegetablePrice = vegetablePrice;
    }

    public static Vegetable fromString(String text) {
        for (Vegetable vegetable : Vegetable.values()) {
            if (vegetable.getSingularName().equals(text)) {
                return vegetable;
            }
        }
        return null;
    }

    public String getNameFromQuantity(int quantity) {
        if (quantity == 1) {
            return singularName;
        } else {
            return pluralName;
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public String getSingularName() {
        return singularName;
    }

    public String getPluralName() {
        return pluralName;
    }

    public int getGrowthTime() {
        return growthTime;
    }

    public VegetablePrice getVegetablePrice() {
        return vegetablePrice;
    }
}
