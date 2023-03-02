package edu.kit.informatik.game.entities;

public class Field {

    public static final String SPACE = "     ";
    private static final String PLANTED_VEGETABLE_STRING = "  %c  ";
    private static final String QUANTITY_STRING = " %d/%d ";
    private final Tiles type;
    private Vegetable plantedVegetable;
    private int growthStage;
    private int quantity;
    private boolean hasGrown;

    public Field(Tiles type) {
        this.type = type;
        hasGrown = false;
    }

    public String getName() {
        return type.getName();
    }

    public boolean isEmpty() {
        return plantedVegetable == null;
    }

    public boolean canPlant(Vegetable vegetable) {
        return type.canPlant(vegetable);
    }

    public void plant(Vegetable vegetable) {
        plantedVegetable = vegetable;
        growthStage = 0;
        quantity = 1;
    }

    public void harvest(int quantity) {
        this.quantity -= quantity;
        if (this.quantity == 0) {
            growthStage = 0;
            plantedVegetable = null;
        }
    }

    public void grow() {
        if (isEmpty()) return;
        growthStage++;
        if (growthStage < plantedVegetable.getGrowthTime()) return;

        growthStage = 0;
        if (quantity * 2 <= type.getCapacity()) {
            quantity *= 2;
            hasGrown = true;
        } // TODO else? Grow to max?
    }

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

    private char getCountdown() {
        return isEmpty() ? '*' : Character.forDigit(plantedVegetable.getGrowthTime() - growthStage, 10); //TODO modify
    }

    public boolean hasGrown() {
        return hasGrown;
    }

    public void setGrown(boolean b) {
        hasGrown = b;
    }

    public int getQuantity() {
        return quantity;
    }

    public Vegetable getPlantedVegetable() {
        return plantedVegetable;
    }
}
