package edu.kit.informatik.game.entities;

import java.util.Set;

public enum Tiles {

    GARDEN(2, "Garden", " G %c ", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO, Vegetable.MUSHROOM)),
    FIELD(4, "Field", " Fi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    LARGE_FIELD(8, "Large Field", "LFi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    FOREST(4, "Forest", " Fo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM)),
    LARGE_FOREST(8, "Large Forest", "LFo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM));

    private final int capacity;
    private final String name;
    private final String printFormat;
    private final Set<Vegetable> growableVegetables;

    Tiles(int capacity, String name, String printFormat, Set<Vegetable> growableVegetables) {
        this.capacity = capacity;
        this.name = name;
        this.printFormat = printFormat;
        this.growableVegetables = growableVegetables;
    }

    public boolean canPlant(Vegetable vegetable) {
        return growableVegetables.contains(vegetable);
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public String getPrintFormat() {
        return printFormat;
    }
}
