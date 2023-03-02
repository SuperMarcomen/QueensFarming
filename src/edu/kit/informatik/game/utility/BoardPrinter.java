package edu.kit.informatik.game.utility;

import edu.kit.informatik.game.entities.Field;
import edu.kit.informatik.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class BoardPrinter {

    public static final String EMPTY_SPACE = "      ";
    public static final String BARN_TEMPLATE = " B %c ";
    private static final String FIELD_TEMPLATE = "|%s|";
    private final Field[][] fields;
    private final char barnCountdown;
    private final int offsetX;
    private final int offsetY;

    public BoardPrinter(Player player) {
        this.fields = player.getFields();
        this.barnCountdown = player.getBarn().getCountdown();
        this.offsetX = player.getOffsetX();
        this.offsetY = player.getOffsetY();
    }

    public List<String> print() {
        List<String> fieldTable = new ArrayList<>();
        int firstFieldX = 0;
        int lastFieldX = 0;
        for (int y = 0; y < fields.length; y++) {
            StringBuilder[] printedRows = { new StringBuilder(), new StringBuilder(), new StringBuilder()};
            for (int x = 0; x < fields[y].length; x++) {
                Field field = fields[y][x];
                if (y - offsetY == 0 && x - offsetX == 0) {
                    printedRows[0].append(String.format(FIELD_TEMPLATE, "     "));
                    printedRows[1].append(String.format(FIELD_TEMPLATE, String.format(BARN_TEMPLATE, barnCountdown)));
                    printedRows[2].append(String.format(FIELD_TEMPLATE, "     "));
                    continue;
                }

                if (field == null) {
                    for (int i = 0; i < 3; i++) {
                        printedRows[i].append(EMPTY_SPACE);
                    }
                    continue;
                }

                if (firstFieldX == 0 || x < firstFieldX) firstFieldX = x;
                if (lastFieldX == 0 || x > lastFieldX) lastFieldX = x;

                String[] printableRows = field.print();
                for (int i = 0; i < printableRows.length; i++) {
                    printedRows[i].append(String.format(FIELD_TEMPLATE,printableRows[i]));
                }
            }
            for (StringBuilder printedRow : printedRows) {
                String string = printedRow.toString();
                fieldTable.add(string);
            }
        }
        fieldTable = formatStrings(fieldTable, firstFieldX, ++lastFieldX);
        /*maxLineLength -= firstFieldX * EMPTY_SPACE.length();

        for (int i = 0; i < fieldTable.size(); i++) {
            String string = fieldTable.get(i);
            string = String.format("%-" + maxLineLength + "s", string);
            fieldTable.set(i, string);
        }*/
        return fieldTable;
    }

    private String removeSpaceEnd(StringBuilder builder) {
        for (int i = builder.length() - 1; i > 0; i--) {
            char c = builder.charAt(i);
            if (Character.isSpaceChar(c)) continue;
            return builder.substring(0, ++i);
        }
        return builder.toString();
    }

    private List<String> formatStrings(List<String> strings, int firstX, int lastX) {
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            if (string.isBlank()) continue;
            string = string.replace("||", "|");
            string = string.substring(firstX * EMPTY_SPACE.length(), lastX * EMPTY_SPACE.length() + 1);
            newStrings.add(string);
        }
        return newStrings;
    }
}
