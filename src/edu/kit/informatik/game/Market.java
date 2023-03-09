package edu.kit.informatik.game;

import edu.kit.informatik.game.entities.Barn;
import edu.kit.informatik.game.entities.Field;
import edu.kit.informatik.game.entities.Player;
import edu.kit.informatik.game.entities.Vegetable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to handle the market and the action
 * of selling and buying vegetables.
 *
 * @author uswry
 * @version 1.0
 */
public class Market {

    private static final String VEGETABLES_SOLD = "You have sold %d vegetable%s for %d gold.";
    private static final String ARTICLE_BOUGHT = "You have bought a %s for %d gold.";
    private static final int FIELD_BASE_PRICE = 10;
    private final Map<Vegetable, Integer> recentlySold;
    private final List<Field> availableFields;

    /**
     * Initializes the needed variables and constants.
     *
     * @param availableFields - A list with the available fields at the start of a game.
     */
    public Market(List<Field> availableFields) {
        this.availableFields = availableFields;
        recentlySold = new HashMap<>();
        for (Vegetable vegetable : Vegetable.values()) {
            recentlySold.put(vegetable, 0);
        }
    }

    /**
     * Returns if there are fields left to buy.
     * @return if there are fields left to buy
     */
    public boolean areThereFieldsLeft() {
        return availableFields.size() > 0;
    }

    /**
     * Buys a field at the given coordinate for the given player and
     * returns the output message.
     *
     * @param player - The player who wants to buy a field
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return the output message
     */
    public String buyField(Player player, int x, int y) {
        int price = getFieldPrice(x, y);
        player.removeGold(price);
        Field field = availableFields.get(0);
        availableFields.remove(0);
        player.addField(field, x, y);
        return String.format(ARTICLE_BOUGHT, field.getName(), price);
    }

    /**
     * Returns the price of the given vegetable.
     *
     * @param vegetable - The vegetable from which to get the price
     * @return the price of the given vegetable
     */
    public int getPriceOf(Vegetable vegetable) {
        return vegetable.getVegetablePrice().getCurrentPrice();
    }

    /**
     * Buys the given player the wanted vegetable and returns
     * the output message.
     *
     * @param player - The player who wants to buy a vegetable
     * @param vegetable - The vegetable to buy
     * @return the output message
     */
    public String buy(Player player, Vegetable vegetable) {
        int price = vegetable.getVegetablePrice().getCurrentPrice();
        player.removeGold(price);
        player.getBarn().addAmountVegetable(vegetable, 1);
        return String.format(ARTICLE_BOUGHT, vegetable.getSingularName(), price);
    }

    /**
     * Sells the wanted vegetable from given player and returns
     * the output message.
     *
     * @param player - The player who wants to sell vegetables
     * @param input - The vegetables to sell
     * @return the output message
     */
    public String sell(Player player, String input) {
        Map<Vegetable, Integer> vegetables = parseInput(player.getBarn(), input);
        int quantity = getTotalQuantity(vegetables.values());
        String plural = quantity == 1 ? "" : "s";
        int gold = sell(vegetables, player);
        return String.format(VEGETABLES_SOLD, quantity, plural, gold);
    }

    /**
     * Returns if the player can sell the given vegetables.
     *
     * @param player - The player who wants to sell vegetables
     * @param input - The vegetables to sell
     * @return if the player can sell the given vegetables
     */
    public boolean canSell(Player player, String input) {
        Map<Vegetable, Integer> vegetables = parseInput(player.getBarn(), input);
        Barn barn = player.getBarn();
        return hasEnough(vegetables, barn);
    }

    /**
     * Adjusts the prices of the market.
     */
    public void adjustPrices() {
        adjustPrice(Vegetable.CARROT, Vegetable.MUSHROOM);
        adjustPrice(Vegetable.SALAD, Vegetable.TOMATO);
    }

    /**
     * Resets the quantity of the recently sold vegetables.
     */
    public void reset() {
        for (Map.Entry<Vegetable, Integer> vegetableEntry : recentlySold.entrySet()) {
            vegetableEntry.setValue(0); //TODO check: does it actually change?
        }
    }

    /**
     * Returns the price of a field at the given coordinates.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return the price of a field at the given coordinates
     */
    public int getFieldPrice(int x, int y) {
        return (Math.abs(x) + Math.abs(y) - 1) * FIELD_BASE_PRICE;
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
        if (input.equals("all")) {
            return barn.getVegetables();
        }
        Map<Vegetable, Integer> vegetables = new HashMap<>();
        if (input.isBlank()) return vegetables;
        String[] args = input.split(" ");
        for (String arg : args) {
            Vegetable vegetable = Vegetable.fromString(arg);
            //int amount = recentlySold.get(vegetable);
            int amount = vegetables.getOrDefault(vegetable, 0);
            vegetables.put(vegetable, 1 + amount);
        }
        return vegetables;
    }
}
