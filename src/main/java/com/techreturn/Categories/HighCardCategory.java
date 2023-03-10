package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
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
            p.setCategory(CATEGORY.HIGHCARD.rank);   //set HighCard Category
            p.setCategoryCard(cList[cList.length-1]);
    }

    @Override
    public Winner rank(Player p1, Player p2) {
            return findWinnerOfHigherCard( p1, Arrays.asList(p1.getValueList()), p2, Arrays.asList(p2.getValueList()));

    }
}
