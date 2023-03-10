package com.techreturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        for (String pname:entry.keySet()){
            name = pname;
            cValue = entry.get(name);
        }
        if (name.equals(p1.getName())){
            Winner winner = new Winner(p1.getName(), p1.getSuitList(), p1.getValueList());
            winner.setWinCard(cValue, 0);
            winner.setCategory(0);  //High Card category
            return winner;
        }else {
            Winner winner = new Winner(p2.getName(), p2.getSuitList(), p2.getValueList());
            winner.setWinCard(cValue, 0);
            winner.setCategory(0);  //High Card category
            return winner;
        }

    }
}
