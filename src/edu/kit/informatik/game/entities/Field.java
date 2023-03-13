package edu.kit.informatik.game.entities;

/**
 * A class that represents a field in the game
 * and all its properties.
 *
 * @author uswry
 * @version 1.0
 */
public class Field {

    private static final String SPACE = "     ";
    private static final int RADIX = 10;
    private static final String PLANTED_VEGETABLE_STRING = "  %c  ";
    private static final String QUANTITY_STRING = " %d/%d ";
    private final Tiles type;
    private Vegetable plantedVegetable;
    private int growthStage;
    private int quantity;
    /**
     * This variable will be true if the vegetable has grown since
     * the last round.
     */
    private boolean hasGrown;
    private int quantityGrown;

    /**
     * Initializes the needed variables and constants.
     *
     * @param type - The type of the field
     */
    public Field(Tiles type) {
        this.type = type;
        hasGrown = false;
        quantityGrown = 0;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return type.getName();
    }

    /**
     * Returns if the field is empty
     * @return if the field is empty
     *
     */
    public boolean isEmpty() {
        return plantedVegetable == null;
    }

    /**
     * Returns if the given vegetable can be planted in this field.
     *
     * @param vegetable - The vegetable to check if it can be planted
     * @return true if the given vegetable can be planted in this field
     */
    public boolean canPlant(Vegetable vegetable) {
        return type.canPlant(vegetable);
    }

    /**
     * Plants the given vegetable and sets the growth stage to 0.
     *
     * @param vegetable - The vegetable to plant
     */
    public void plant(Vegetable vegetable) {
        plantedVegetable = vegetable;
        growthStage = 0;
        quantity = 1;
    }

    /**
     * Harvests the given amount of the given vegetable.
     *
     * @param quantity - The vegetable to plant
     */
    public void harvest(int quantity) {
        this.quantity -= quantity;
        if (this.quantity == 0) {
            growthStage = 0;
            plantedVegetable = null;
        }
    }

    /**
     * Grows the field if it's not empty, or it increases the growth stage.
     */
    public void grow() {
        if (isEmpty()) return;
        if (quantity == type.getCapacity()) return;
        growthStage++;
        if (growthStage < plantedVegetable.getGrowthTime()) return;

        quantityGrown = quantity;
        growthStage = 0;
        hasGrown = true;
        if (quantity * 2 < type.getCapacity()) {
            quantity *= 2;
        } else {
            quantity = type.getCapacity();
        }
        quantityGrown -= quantity;
        quantityGrown = -quantityGrown;
    }

    /**
     * Prints the field.
     *
     * @return an array of String to be printed on the console
     */
    public String[] print() {
        String[] lines = new String[3];
        lines[0] = String.format(type.getPrintFormat(), getCountdown());
        if (!isEmpty()) {
            lines[1] = String.format(PLANTED_VEGETABLE_STRING, plantedVegetable.getSymbol());
        } else {
            lines[1] = SPACE;
        }
        lines[2] = String.format(QUANTITY_STRING, quantity, type.getCapacity());
        return lines;
    }

    /**
     * Returns the countdown. It's a star if the field is or empty
     * and a number otherwise.
     *
     * @return the countdown
     */
    private char getCountdown() {
        if (quantity == type.getCapacity()) return '*';
        return isEmpty()
                ? '*' : Character.forDigit(plantedVegetable.getGrowthTime() - growthStage, RADIX);
    }

    /**
     * Returns if the vegetable has grown.
     *
     * @return if the vegetable has grown.
     */
    public boolean hasGrown() {
        return hasGrown;
    }

    /**
     * Sets the new value for the variable grown.
     *
     * @param grown - the new value for the variable grown
     */
    public void setGrown(boolean grown) {
        hasGrown = grown;
    }

    /**
     * Returns the quantity of the vegetable.
     *
     * @return the quantity of the vegetable
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the planted vegetable.
     *
     * @return the planted vegetable
     */
    public Vegetable getPlantedVegetable() {
        return plantedVegetable;
    }

    /**
     * Returns the quantity of newly grown vegetables.
     *
     * @return the quantity of newly grown vegetables
     */
    public int getQuantityGrown() {
        return quantityGrown;
    }
}
