package edu.kit.informatik.game.entities;

public class Field {

    public static final String SPACE = "     ";
    private static final String PLANTED_VEGETABLE_STRING = "  %c  ";
    private static final String QUANTITY_STRING = " %d/%d ";
    private final Tiles type;
    private Vegetable plantedVegetable;
    private int growthStage;
    private int quantity;

    public Field(Tiles type) {
        this.type = type;
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

    public void grow() {
        if (isEmpty()) return;
        growthStage++;
        if (growthStage < plantedVegetable.getGrowthTime()) return;

        growthStage = 0;
        if (quantity * 2 <= type.getCapacity()) {
            quantity *= 2;
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

}
