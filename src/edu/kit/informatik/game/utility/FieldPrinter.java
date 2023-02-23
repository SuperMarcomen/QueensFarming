package edu.kit.informatik.game.utility;

import edu.kit.informatik.game.entities.Field;
import edu.kit.informatik.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class FieldPrinter {

    public static final String EMPTY_SPACE = "      ";
    public static final String BARN_TEMPLATE = " B %c ";
    private static final String FIELD_TEMPLATE = "|%s|";
    private final Field[][] fields;
    private final int totalVegetables;
    private final int offsetX;
    private final int offsetY;

    public FieldPrinter(Player player) {
        this.fields = player.getFields();
        this.totalVegetables = player.getTotalVegetables();
        this.offsetX = player.getOffsetX();
        this.offsetY = player.getOffsetY();
    }

    public List<String> print() {
        List<String> fieldTable = new ArrayList<>();
        int firstFieldX = -1;
        for (int y = 0; y < fields.length; y++) {
            StringBuilder[] printedRows = { new StringBuilder(), new StringBuilder(), new StringBuilder()};
            for (int x = 0; x < fields[y].length; x++) {
                Field field = fields[y][x];
                if (y - offsetY == 0 && x - offsetX == 0) {
                    printedRows[0].append(String.format(FIELD_TEMPLATE, "     "));
                    printedRows[1].append(String.format(FIELD_TEMPLATE, String.format(BARN_TEMPLATE, totalVegetables)));
                    printedRows[2].append(String.format(FIELD_TEMPLATE, "     "));
                    continue;
                }

                if (field == null) {
                    for (int i = 0; i < 3; i++) {
                        printedRows[i].append(EMPTY_SPACE);
                    }
                    continue;
                }

                if (firstFieldX == -1 || x < firstFieldX) firstFieldX = x;

                String[] printableRows = field.print();
                for (int i = 0; i < printableRows.length; i++) {
                    printedRows[i].append(String.format(FIELD_TEMPLATE,printableRows[i]));
                }
            }
            for (StringBuilder printedRow : printedRows) {
                fieldTable.add(printedRow.toString());
            }
        }
        fieldTable = formatStrings(fieldTable, firstFieldX);
        return fieldTable;
    }

    private List<String> formatStrings(List<String> strings, int firstX) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            if (string.isBlank()) continue;
            string = string.substring(firstX * EMPTY_SPACE.length());
            string = string.replace("||", "|");
            newStrings.add(string);
        }
        return newStrings;
    }
}
