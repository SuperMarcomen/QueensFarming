package edu.kit.informatik.game.entities;

import edu.kit.informatik.game.utility.VegetableComparator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class to handle the barn and the vegetables it contains.
 *
 * @author uswry
 * @version 1.0
 */
public class Barn {

    private static final int RADIX = 10;
    private static final int COUNTDOWN_START = 6;
    private int countdown;
    private final Map<Vegetable, Integer> quantities;
    private boolean haveVegetablesSpoiled;

    /**
     * Initializing the needed variables and constants.
     */
    public Barn() {
        quantities = new LinkedHashMap<>();
        haveVegetablesSpoiled = false;
        countdown = COUNTDOWN_START;
        quantities.put(Vegetable.CARROT, 1);
        quantities.put(Vegetable.MUSHROOM, 1);
        quantities.put(Vegetable.SALAD, 1);
        quantities.put(Vegetable.TOMATO, 1);
    }

    /**
     * Checks if the vegetable is present in the barn.
     *
     * @param vegetable - The vegetable to be checked
     * @return true if the vegetable is present
     */
    public boolean hasVegetable(Vegetable vegetable) {
        return quantities.get(vegetable) >= 1;
    }

    /**
     * Checks the countdown and decreases its value if it's bigger than 0.
     */
    public void checkCountdown() {
        for (Integer value : quantities.values()) {
            if (value > 0) return;
        }
        countdown = 0;
    }

    /**
     * Decreases the countdown value if it's bigger than 0.
     */
    public void reduceCountdown() {
        if (countdown > 0) countdown--;
    }

    /**
     * Spoils the vegetables and removes them from the barn.
     */
    public void spoilVegetables() {
        haveVegetablesSpoiled = true;
        for (Vegetable vegetable : quantities.keySet()) {
            quantities.put(vegetable, 0);
        }
    }

    /**
     * Returns if the vegetables have spoiled.
     *
     * @return true if the vegetables have spoiled
     */
    public boolean haveVegetablesSpoiled() {
        return haveVegetablesSpoiled;
    }

    /**
     * Sets if the vegetables have spoiled.
     *
     * @param haveVegetablesSpoiled - The status of the vegetables
     */
    public void setVegetablesSpoiled(boolean haveVegetablesSpoiled) {
        this.haveVegetablesSpoiled = haveVegetablesSpoiled;
    }

    /**
     * Returns the vegetables which quantity is bigger than 0.
     *
     * @return the vegetables which quantity is bigger than 0.
     */
    public Vegetable[] getAvailableVegetables() {
        List<Vegetable> availableVegetables = new ArrayList<>();
        for (Map.Entry<Vegetable, Integer> vegetableEntry : quantities.entrySet()) {
            Vegetable vegetable = vegetableEntry.getKey();
            int quantity = vegetableEntry.getValue();
            if (quantity > 0) {
                availableVegetables.add(vegetable);
            }
        }
        return availableVegetables.toArray(new Vegetable[0]);
    }

    /**
     * Returns the sorted vegetables in decreasing order based on the
     * quantity and the name.
     *
     * @return the sorted vegetables
     */
    public Map<Vegetable, Integer> getSortedVegetables() {
        Map<Vegetable, Integer> sortedVegetables = quantities.entrySet().stream()
                .sorted(new VegetableComparator())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedVegetables;
    }

    /**
     * Returns the vegetables map.
     *
     * @return the vegetables map
     */
    public Map<Vegetable, Integer> getVegetables() {
        return quantities;
    }

    /**
     * Returns the amount of the given vegetable.
     *
     * @param vegetable - The vegetable to check
     * @return the quantity of the vegetable
     */
    public int getAmountOf(Vegetable vegetable) {
        return quantities.get(vegetable);
    }

    /**
     * Adds the given amount to the map containing the vegetables.
     *
     * @param vegetable - The vegetable whose quantity will be increased
     * @param amount - The amount to add to the vegetable
     */
    public void addAmountVegetable(Vegetable vegetable, int amount) {
        if (getTotalVegetables() == 0) {
            countdown = COUNTDOWN_START;
        }
        quantities.put(vegetable, quantities.get(vegetable) + amount);
    }

    /**
     * Removes the given amount from the map containing the vegetables.
     *
     * @param vegetable - The vegetable whose quantity will be decreased
     * @param amount - The amount to remove from the vegetable
     */
    public void removeAmountVegetable(Vegetable vegetable, int amount) {
        quantities.put(vegetable, quantities.get(vegetable) - amount);
    }

    /**
     * Returns the total amount of vegetables.
     *
     * @return the total amount of vegetables
     */
    public int getTotalVegetables() {
        int sum = 0;
        for (Integer value : quantities.values()) {
            sum += value;
        }
        return sum;
    }

    /**
     * Returns the int countdown.
     * @return the int countdown
     */
    public int getIntCountdown() {
        return countdown;
    }

    /**
     * Returns the countdown.
     * @return the countdown
     */
    public char getCountdown() {
        if (getTotalVegetables() == 0) return '*';
        return countdown == 0 ? '*' : Character.forDigit(countdown, RADIX);
    }
}
