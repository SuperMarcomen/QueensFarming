package edu.kit.informatik.game.entities;

/**
 * A class to represent the player of the game and
 * contains all of its properties.
 *
 * @author uswry
 * @version 1.0
 */
public class Player implements Comparable<Player> {

    private static final int OFFSET_X = 20;
    private static final int OFFSETY = 20;
    private final int id;
    private final String name;
    private int gold;
    private final Barn barn;
    private final Field[][] fields;


    /**
     * Initializes the field with its standard values and
     * initializes the needed variables and constants.
     *
     * @param id - The id of the player
     * @param name - The name of the player
     * @param gold - The amount of starting gold
     */
    public Player(int id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.barn = new Barn();
        fields = new Field[OFFSETY + 1][OFFSET_X + OFFSET_X];
        fields[OFFSETY][-1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSETY][1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSETY - 1][OFFSET_X] = new Field(Tiles.FIELD);
    }

    /**
     * Add a field at the given coordinate.
     *
     * @param field - The field to add
     * @param x - The x coordinate
     * @param y - The y coordinate
     */
    public void addField(Field field, int x, int y) {
        fields[OFFSETY - y][x + OFFSET_X] = field;
    }

    /**
     * Returns the vegetable at the given coordinates.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return the vegetable at the given coordinates
     */
    public Vegetable getVegetableAt(int x, int y) {
        return getField(x, y).getPlantedVegetable();
    }

    /**
     * Returns if the player can reach the given field.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return if the player can reach the given field
     */
    public boolean canReachField(int x, int y) {
        boolean up = isFieldAvailable(x, y - 1);
        boolean left = isFieldAvailable(x - 1, y);
        boolean right = isFieldAvailable(x + 1, y);
        return up || left || right;
    }

    /**
     * Returns if the field at the given coordinates has
     * enough vegetables.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @param quantity - The quantity of vegetables the field
     *                 has to contain
     * @return if the field at the given coordinates has
     *          enough vegetables.
     */
    public boolean hasFieldEnoughVegetables(int x, int y, int quantity) {
        if (isFieldEmpty(x, y)) return false;
        return getField(x, y).getQuantity() >= quantity;
    }

    /**
     * Returns if the field at the given coordinates is empty.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return if the field at the given coordinates is empty
     */
    public boolean isFieldEmpty(int x, int y) {
        return getField(x, y).isEmpty();
    }

    /**
     * Returns if the player has the given vegetable in its barn.
     *
     * @param vegetable - The vegetable the player should have
     * @return if the player has the given vegetable in its barn.
     */
    public boolean hasVegetable(Vegetable vegetable) {
        return barn.hasVegetable(vegetable);
    }

    /**
     * Checks if the given vegetable can be planted on the given field.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @param vegetable - The vegetable to be checked
     * @return if the given vegetable can be planted on the given field
     */
    public boolean canPlantOnField(int x, int y, Vegetable vegetable) {
        return getField(x, y).canPlant(vegetable);
    }

    /**
     * Plants the given vegetable at the given coordinates.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @param vegetable - The vegetable to plant
     */
    public void plant(int x, int y, Vegetable vegetable) {
        getField(x, y).plant(vegetable);
        barn.removeAmountVegetable(vegetable, 1);
    }

    /**
     * Harvest the given quantity of the given vegetable
     * at the given coordinates.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @param quantity - The quantity to be harvested
     */
    public void harvest(int x, int y, int quantity) {
        Field field = getField(x, y);
        Vegetable plantedVegetable = field.getPlantedVegetable();
        field.harvest(quantity);
        barn.addAmountVegetable(plantedVegetable, quantity);
    }

    /**
     * Loops through the fields and lets them grow.
     */
    public void growFields() {
        for (Field[] fieldRow : fields) {
            for (Field field : fieldRow) {
                if (field == null) continue;
                field.grow();
            }
        }
    }

    /**
     * Returns the number of vegetables in the barn.
     *
     * @return the number of vegetables in the barn.
     */
    public int getNumberGrownVegetables() {
        int amount = 0;
        for (Field[] fieldRow : fields) {
            for (Field field : fieldRow) {
                if (field == null) continue;
                if (!field.hasGrown()) continue;
                field.setGrown(false);
                amount += field.getQuantityGrown();
            }
        }
        return amount;
    }

    /**
     * Returns if the field at the given coordinates is available.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return if the field at the given coordinates is available.
     */
    public boolean isFieldAvailable(int x, int y) {
        if (x + OFFSET_X > fields[0].length - 1 || Math.abs(OFFSETY - y) > fields.length - 1 || y < 0) return false;
        if (x == 0 && y == 0) return false; // barn
        return getField(x, y) != null;
    }

    /**
     * Returns the field at the given coordinates.
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return the field at the given coordinates
     */
    private Field getField(int x, int y) {
        return fields[OFFSETY - y][x + OFFSET_X];
    }

    /**
     * Returns if the player has enough money.
     *
     * @param amount - The amount of money the player should have
     * @return if the player has enough money.
     */
    public boolean hasEnoughMoney(int amount) {
        return gold >= amount;
    }

    /**
     * Adds the given amount of gold to the player.
     *
     * @param amount - The amount to add
     */
    public void addGold(int amount) {
        gold += amount;
    }

    /**
     * Removes the given amount of gold from the player.
     *
     * @param amount - The amount to remove
     */
    public void removeGold(int amount) {
        gold -= amount;
    }

    /**
     * Returns the barn.
     * @return the barn
     */
    public Barn getBarn() {
        return barn;
    }

    /**
     * Returns the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the gold.
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Returns the fields.
     * @return the fields
     */
    public Field[][] getFields() {
        return fields;
    }

    /**
     * Returns the x offset.
     * @return the x offset
     */
    public int getOffsetX() {
        return OFFSET_X;
    }

    /**
     * Returns the y offset.
     * @return the y offset
     */
    public int getOffsetY() {
        return OFFSETY;
    }

    @Override
    public int compareTo(Player o) {
        return o.getGold() - this.gold;
    }
}
