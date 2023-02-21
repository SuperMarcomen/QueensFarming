package edu.kit.informatik.game.entities;

import java.util.Set;

public enum Tiles {

    GARDEN(2, " G %c ", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO, Vegetable.MUSHROOM)),
    FIELD(4, " Fi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    LARGE_FIELD(8, "LFi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    FOREST(4, " Fo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM)),
    LARGE_FOREST(8, "LFo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM));

    private final int capacity;
    private final String printFormat;
    private final Set<Vegetable> growableVegetables;

    Tiles(int capacity, String printFormat, Set<Vegetable> growableVegetables) {
        this.capacity = capacity;
        this.printFormat = printFormat;
        this.growableVegetables = growableVegetables;
    }

    public boolean canPlant(Vegetable vegetable) {
        return growableVegetables.contains(vegetable);
    }

    public int getCapacity() {
        return capacity;
    }

    public String getPrintFormat() {
        return printFormat;
    }
}
