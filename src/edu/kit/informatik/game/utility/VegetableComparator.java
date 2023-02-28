package edu.kit.informatik.game.utility;

import edu.kit.informatik.game.entities.Vegetable;

import java.util.Comparator;
import java.util.Map;

public class VegetableComparator implements Comparator<Map.Entry<Vegetable, Integer>> {
    @Override
    public int compare(Map.Entry<Vegetable, Integer> o1, Map.Entry<Vegetable, Integer> o2) {
        int result =  o1.getValue().compareTo(o2.getValue());
        if (result != 0) return result;
        else return o1.getKey().getPluralName().compareTo(o2.getKey().getPluralName());
    }
}
