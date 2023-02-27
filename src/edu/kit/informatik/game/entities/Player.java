package edu.kit.informatik.game.entities;

public class Player implements Comparable<Player> {

    private final int id;
    private final String name;
    private int gold;
    private final Barn barn;
    private final Field[][] fields;
    private final int OFFSET_X = 10;
    private final int OFFSET_Y = 20;


    public Player(int id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.barn = new Barn();
        fields = new Field[OFFSET_Y + 1][OFFSET_Y + 1];
        fields[OFFSET_Y][-1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y][1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y - 1][OFFSET_X] = new Field(Tiles.FIELD);
    }

    public Vegetable getVegetableAt(int x, int y) {
        return fields[OFFSET_Y - y][x + OFFSET_X].getPlantedVegetable();
    }

    public boolean isFieldAvailable(int x, int y) {
        if (x + OFFSET_X > fields.length - 1 || OFFSET_Y - y > fields[0].length - 1) return false;
        return fields[OFFSET_Y - y][x + OFFSET_X] != null;
    }

    public boolean hasFieldEnoughVegetables(int x, int y, int quantity) {
        if (isFieldEmpty(x, y)) return false;
        return fields[OFFSET_Y - y][x + OFFSET_X].getQuantity() >= quantity;
    }

    public boolean isFieldEmpty(int x, int y) {
        return fields[OFFSET_Y - y][x + OFFSET_X].isEmpty();
    }

    public boolean hasVegetable(Vegetable vegetable) {
        return barn.hasVegetable(vegetable);
    }

    public boolean canPlantOnField(int x, int y, Vegetable vegetable) {
        return fields[OFFSET_Y - y][x + OFFSET_X].canPlant(vegetable);
    }

    public void plant(int x, int y, Vegetable vegetable) {
        fields[OFFSET_Y - y][x + OFFSET_X].plant(vegetable);
        barn.removeAmountVegetable(vegetable, 1);
    }

    public void harvest(int x, int y, int quantity) {
        Field field = fields[OFFSET_Y - y][x + OFFSET_X];
        field.harvest(quantity);
        barn.addAmountVegetable(field.getPlantedVegetable(), quantity);
    }

    public void growFields() {
        for (Field[] fieldRow : fields) {
            for (Field field : fieldRow) {
                if (field == null) continue;
                field.grow();
            }
        }
    }

    public boolean hasEnoughMoney(int amount) {
        return gold >= amount;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void removeGold(int amount) {
        gold -= amount;
    }

    public Barn getBarn() {
        return barn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public Field[][] getFields() {
        return fields;
    }

    public char getBarnCountdown() {
        return barn.getCountdown();
    }

    public int getTotalVegetables() {
        return barn.getTotalVegetables();
    }

    public int getOffsetX() {
        return OFFSET_X;
    }

    public int getOffsetY() {
        return OFFSET_Y;
    }

    @Override
    public int compareTo(Player o) {
        return this.gold - o.getGold();
    }
}
