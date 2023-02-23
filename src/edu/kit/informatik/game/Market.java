package edu.kit.informatik.game;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.*;

public class Market {

    private static final String VEGETABLES_SOLD = "You have sold %d vegetable%s for %d gold.";
    private final Map<Vegetable, Integer> recentlySold;

    public Market() {
        recentlySold = new HashMap<>();
        for (Vegetable vegetable : Vegetable.values()) {
            recentlySold.put(vegetable, 0);
        }
    }

    public String sell(Player player, String input) { // TODO sell 0 vegetable? Should work
        Map<Vegetable, Integer> vegetables = parseInput(player.getBarn(), input);
        int quantity = getTotalQuantity(vegetables.values());
        String  plural = quantity <= 1 ? "" : "s";
        int gold = sell(vegetables, player);
        return String.format(VEGETABLES_SOLD, quantity, plural, gold);
    }

    public boolean canSell(Player player, String input) {
        Map<Vegetable, Integer> vegetables = parseInput(player.getBarn(), input);
        Barn barn = player.getBarn();
        return hasEnough(vegetables, barn);
    }

    private int sell(Map<Vegetable, Integer> vegetables, Player player) {
        Barn barn = player.getBarn();
        int totalAmount = 0;
        addRecentlySold(vegetables);
        for (Map.Entry<Vegetable, Integer> vegetableEntry : vegetables.entrySet()) {
            Vegetable vegetable = vegetableEntry.getKey();
            int amount = vegetableEntry.getValue();
            barn.removeAmountVegetable(vegetable, amount);
            int gold = vegetable.getVegetablePrice().getCurrentPrice() * amount;
            totalAmount += gold;
            player.addGold(gold);
        }
        barn.checkCountdown();
        return totalAmount;
    }

    public void adjustPrices() {
        adjustPrice(Vegetable.CARROT, Vegetable.MUSHROOM);
        adjustPrice(Vegetable.SALAD, Vegetable.TOMATO);
    }

    public void reset() {
        for (Map.Entry<Vegetable, Integer> vegetableEntry : recentlySold.entrySet()) {
            vegetableEntry.setValue(0); //TODO check: does it actually change?
        }
    }

    private void adjustPrice(Vegetable first, Vegetable second) {
        int amountFirst = recentlySold.get(first);
        int amountSecond = recentlySold.get(second);
        int difference = Math.abs(amountFirst - amountSecond);
        int indexDifference = difference / 2;
        if (indexDifference == 0) return;

        for (int i = 0; i < indexDifference; i++) {
            if (amountFirst > amountSecond) {
                first.getVegetablePrice().decreasePrice();
                second.getVegetablePrice().increasePrice();
            } else {
                first.getVegetablePrice().increasePrice();
                second.getVegetablePrice().decreasePrice();
            }
        }
    }

    private void addRecentlySold(Map<Vegetable, Integer> vegetables) {
        for (Map.Entry<Vegetable, Integer> vegetableEntry : vegetables.entrySet()) {
            Vegetable vegetable = vegetableEntry.getKey();
            int amount = vegetableEntry.getValue();
            recentlySold.put(vegetable, amount + recentlySold.get(vegetable));
        }
    }

    private int getTotalQuantity(Collection<Integer> collection) {
        int sum = 0;
        for (Integer integer : collection) {
            sum += integer;
        }
        return sum;
    }

    private boolean hasEnough(Map<Vegetable, Integer> vegetables, Barn barn) {
        for (Map.Entry<Vegetable, Integer> vegetableEntry : vegetables.entrySet()) {
            if (barn.getAmountOf(vegetableEntry.getKey()) < vegetableEntry.getValue()) return false;
        }
        return true;
    }

    private Map<Vegetable, Integer> parseInput(Barn barn, String input) {
        if (input.equals("all")) return barn.getVegetables();
        Map<Vegetable, Integer> vegetables = new HashMap<>();
        if (input.isBlank()) return vegetables;
        String[] args = input.split(" ");
        for (String arg : args) {
            Vegetable vegetable = Vegetable.fromString(arg);
            int amount = recentlySold.get(vegetable);
            amount += 1;
            vegetables.put(vegetable, amount);
        }
        return vegetables;
    }
    
    public List<String> print() {
        List<String> strings = new ArrayList<>();
        for (Vegetable value : Vegetable.values()) {

        }
        return strings;
    }
}
