package edu.kit.informatik.game.entities;

public class Player implements Comparable<Player> {

    private final int id;
    private final String name;
    private int gold;
    private final Barn barn;
    private final Field[][] fields;
    private final int OFFSET_X = 20;
    private final int OFFSET_Y = 20;


    public Player(int id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.barn = new Barn();
        fields = new Field[OFFSET_Y + OFFSET_Y][OFFSET_X + OFFSET_X];
        fields[OFFSET_Y][-1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y][1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y - 1][OFFSET_X] = new Field(Tiles.FIELD);
    }

    public void addField(Field field, int x, int y) {
        fields[OFFSET_Y - y][x + OFFSET_X] = field;
    }

    public Vegetable getVegetableAt(int x, int y) {
        return getField(x, y).getPlantedVegetable();
    }

    public boolean canReachField(int x, int y) {
        boolean up = isFieldAvailable(x, y - 1 );
        boolean down = isFieldAvailable(x, y + 1 );
        boolean left = isFieldAvailable(x - 1, y);
        boolean right = isFieldAvailable(x + 1, y);
        return up || down || left || right;
    }
    
    public boolean isFieldAvailable(int x, int y) {
        if (x + OFFSET_X > fields.length - 1 || OFFSET_Y - y > fields[0].length - 1) return false;
        if (x == 0 && y == 0) return true; // barn
        return getField(x, y) != null;
    }

    public boolean hasFieldEnoughVegetables(int x, int y, int quantity) {
        if (isFieldEmpty(x, y)) return false;
        return getField(x, y).getQuantity() >= quantity;
    }

    public boolean isFieldEmpty(int x, int y) {
        return getField(x, y).isEmpty();
    }

    public boolean hasVegetable(Vegetable vegetable) {
        return barn.hasVegetable(vegetable);
    }

    public boolean canPlantOnField(int x, int y, Vegetable vegetable) {
        return getField(x, y).canPlant(vegetable);
    }

    public void plant(int x, int y, Vegetable vegetable) {
        getField(x, y).plant(vegetable);
        barn.removeAmountVegetable(vegetable, 1);
    }

    public void harvest(int x, int y, int quantity) {
        Field field = getField(x, y);
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
    
    private Field getField(int x, int y) {
        return fields[OFFSET_Y - y][x + OFFSET_X];
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
