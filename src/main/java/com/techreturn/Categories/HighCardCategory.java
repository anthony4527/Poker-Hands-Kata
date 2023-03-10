package com.techreturn.Categories;

import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class HighCardCategory extends HighCard implements ICategory {
        @Override
    public void match(Player p) {
        //return the highest card
            String[] cList = p.getValueList();
            //reorder the two list for identifying the higher one
            orderValue(cList);
            p.setCategory(0);   //set HighCard Category
            p.setCategoryCard(cList[cList.length-1]);

    }

    @Override
    public Winner rank(Player p1, Player p2) {
        Map<String, String> entry = compareValueList (p1.getName(), Arrays.asList(p1.getValueList()), p2.getName(),
                Arrays.asList(p2.getValueList()));
        //
        String name ="";
        String cValue = "";
        ArrayList<String> wList = new ArrayList<>();
        for (String pname : entry.keySet()) {
            name = pname;
            cValue = entry.get(name);
            wList.add(cValue);
        }
        if (name.equals(p1.getName())) {
            return prepareWinner(p1, 0, wList); // rank by high card
        } else if (name.equals(p2.getName())) {
            return prepareWinner(p2, 0, wList);
        }
        return null; //null means no winner i.e. a Tie


    }
}
