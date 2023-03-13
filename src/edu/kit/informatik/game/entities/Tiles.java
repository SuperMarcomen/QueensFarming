package edu.kit.informatik.game.entities;

import java.util.Set;

/**
 * It describes the type of field there are in this game
 * and contains their properties.
 *
 * @author uswry
 * @version 1.0
 */
public enum Tiles {

    /**
     * The garden tile.
     */
    GARDEN(2, "Garden", " G %c ", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO, Vegetable.MUSHROOM)),
    /**
     * The field tile.
     */
    FIELD(4, "Field", " Fi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    /**
     * The large field tile.
     */
    LARGE_FIELD(8, "Large Field", "LFi %c", Set.of(Vegetable.CARROT, Vegetable.SALAD, Vegetable.TOMATO)),
    /**
     * The forest tile.
     */
    FOREST(4, "Forest", " Fo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM)),
    /**
     * The large forest tile.
     */
    LARGE_FOREST(8, "Large Forest", "LFo %c", Set.of(Vegetable.CARROT, Vegetable.MUSHROOM));

    private final int capacity;
    private final String name;
    private final String printFormat;
    private final Set<Vegetable> growableVegetables;

    /**
     * Initializes the needed variables and constants.
     *
     * @param capacity - The capacity of the tile
     * @param name - The name of the tile
     * @param printFormat - The print format of the tile
     * @param growableVegetables - The growable vegetables on this tile
     */
    Tiles(int capacity, String name, String printFormat, Set<Vegetable> growableVegetables) {
        this.capacity = capacity;
        this.name = name;
        this.printFormat = printFormat;
        this.growableVegetables = growableVegetables;
    }

    /**
     * Returns if the given vegetable can be plated on the field.
     *
     * @param vegetable - The vegetable to be checked.
     * @return if the given vegetable can be plated on the field.
     */
    public boolean canPlant(Vegetable vegetable) {
        return growableVegetables.contains(vegetable);
    }

    /**
     * Returns the capacity of the tile.
     * @return the capacity of the tile.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the name of the field.
     * @return the name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the print format.
     * @return the print format
     */
    public String getPrintFormat() {
        return printFormat;
    }
}
