package edu.kit.informatik.game.entities;

import edu.kit.informatik.game.utility.VegetableComparator;

import java.util.*;
import java.util.stream.Collectors;

public class Barn {

    private static final int COUNTDOWN_START = 6;
    public static final int RADIX = 10;
    private int countdown;
    private final Map<Vegetable, Integer> quantities;
    private boolean haveVegetablesSpoiled;

    public Barn() {
        quantities = new LinkedHashMap<>();
        haveVegetablesSpoiled = false;
        countdown = COUNTDOWN_START;
        quantities.put(Vegetable.CARROT, 1);
        quantities.put(Vegetable.MUSHROOM, 1);
        quantities.put(Vegetable.SALAD, 1);
        quantities.put(Vegetable.TOMATO, 1);
    }

    public boolean hasVegetable(Vegetable vegetable) {
        return quantities.get(vegetable) >= 1;
    }

    public void checkCountdown() {
        for (Integer value : quantities.values()) {
            if (value > 0) return;
        }
        countdown = 0;
    }

    public void reduceCountdown() {
        if (countdown > 0) countdown--;
    }

    public void spoilVegetables() {
        haveVegetablesSpoiled = true;
        for (Vegetable vegetable : quantities.keySet()) {
            quantities.put(vegetable, 0);
        }
    }

    public boolean haveVegetablesSpoiled() {
        return haveVegetablesSpoiled;
    }

    public void setVegetablesSpoiled(boolean haveVegetablesSpoiled) {
        this.haveVegetablesSpoiled = haveVegetablesSpoiled;
    }

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

    public void addAmountVegetable(Vegetable vegetable, int amount) {
        if (getTotalVegetables() == 0) {
            countdown = COUNTDOWN_START;
        }
        quantities.put(vegetable, quantities.get(vegetable) + amount);
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
        if (getTotalVegetables() == 0) return '*';
        return countdown == 0 ? '*' : Character.forDigit(countdown, RADIX);
    }
}
