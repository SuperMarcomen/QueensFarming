package edu.kit.informatik.game.entities;

import edu.kit.informatik.game.utility.VegetableComparator;

import java.util.*;
import java.util.stream.Collectors;

public class Barn {

    private static final int COUNTDOWN_START = 6;
    private int countdown;
    private final Map<Vegetable, Integer> quantities;

    public Barn() {
        quantities = new LinkedHashMap<>();
        countdown = COUNTDOWN_START;
        quantities.put(Vegetable.CARROT, 1);
        quantities.put(Vegetable.MUSHROOM, 1);
        quantities.put(Vegetable.SALAD, 1);
        quantities.put(Vegetable.TOMATO, 1);
    }

    public void checkCountdown() {
        for (Integer value : quantities.values()) {
            if (value > 0) return;
        }
        countdown = 0;
    }

    public Map<Vegetable, Integer> getSortedVegetables() {
        Map<Vegetable, Integer> sortedVegetables = quantities.entrySet().stream()
                .sorted(new VegetableComparator())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedVegetables;
    }

    public Map<Vegetable, Integer> getVegetables() {
        return quantities;
    }

    public int getAmountOf(Vegetable vegetable) {
        return quantities.get(vegetable);
    }

    public void removeAmountVegetable(Vegetable vegetable, int amount) {
        quantities.put(vegetable, quantities.get(vegetable) - amount);
    }

    public int getTotalVegetables() {
        int sum = 0;
        for (Integer value : quantities.values()) {
            sum += value;
        }
        return sum;
    }

    public int getIntCountdown() {
        return countdown;
    }

    public char getCountdown() {
        return countdown == 0 ? '*' : (char) 0;
    }
}
