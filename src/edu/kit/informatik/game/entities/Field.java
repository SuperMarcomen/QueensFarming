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
    private int quantityGrown;

    public Field(Tiles type) {
        this.type = type;
        hasGrown = false;
        quantityGrown = 0;
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
        if (quantity == type.getCapacity()) return;
        growthStage++;
        if (growthStage < plantedVegetable.getGrowthTime()) return;

        quantityGrown = quantity;
        growthStage = 0;
        hasGrown = true;
        if (quantity * 2 < type.getCapacity()) {
            quantity *= 2;
        } else {
            quantity = type.getCapacity(); // TODO else? Grow to max?
        }
        quantityGrown -= quantity;
        quantityGrown = -quantityGrown;
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
        if (quantity == type.getCapacity()) return '*';
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

    public int getQuantityGrown() {
        return quantityGrown;
    }
}
