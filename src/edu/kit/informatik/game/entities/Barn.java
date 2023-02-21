package edu.kit.informatik.game.entities;

import java.util.*;

public class Barn {

    private final int COUNTDOWN_START = 6;
    private final String BARN_TITLE = "Barn (spoils in %d turns)";
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

    public List<String> print() {
        List<String> strings = new ArrayList<>();
        strings.add(String.format(BARN_TITLE, countdown));
        int sum = 0;
        for (Vegetable vegetable : quantities.keySet()) {
            int vegetableQuantity = quantities.get(vegetable);
            if (vegetableQuantity == 0) continue;
            strings.add(String.format("%-12s %d", vegetable.getPluralName() + ':', vegetableQuantity));
            sum += vegetableQuantity;
        }
        // TODO what if barn empty? Still send ------
        strings.add(String.format("%-13s", "").replace(" ", "-"));
        strings.add(String.format("%-12s %d", "Sum:", sum));

        return strings;
    }

    public void checkCountdown() {
        for (Integer value : quantities.values()) {
            if (value > 0) return;
        }
        countdown = 0;
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

    public char getCountdown() {
        return countdown == 0 ? '*' : (char) 0;
    }
}
