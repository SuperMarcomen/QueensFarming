package edu.kit.informatik.game.entities;

import java.util.List;

public class Player {

    private final String name;
    private int gold;
    private final Barn barn;
    private final Field[][] fields;
    private final int OFFSET_X = 10;
    private final int OFFSET_Y = 20;


    public Player(String name, int gold) {
        this.name = name;
        this.gold = gold;
        this.barn = new Barn();
        fields = new Field[OFFSET_Y + 1][OFFSET_Y + 1];
        fields[OFFSET_Y - 0][-1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y - 0][1 + OFFSET_X] = new Field(Tiles.GARDEN);
        fields[OFFSET_Y - 1][OFFSET_X] = new Field(Tiles.FIELD);
    }

    public List<String> printBarn() {
        return barn.print();
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
}
