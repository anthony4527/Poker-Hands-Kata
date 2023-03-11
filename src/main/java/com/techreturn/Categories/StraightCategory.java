package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import com.techreturn.Enum.VALUE;

import java.util.ArrayList;
import java.util.List;

public class StraightCategory extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        String[] sList = p.getValueList();
        //reorder the two list for identifying the higher one
        orderValue(sList);
        int listSize = sList.length;
        int count =0;
        for (int i=0; i<listSize-1; i++){
            if ((VALUE.getValue(sList[listSize-1-i]).score - VALUE.getValue(sList[listSize-2-i]).score) != 1){
                break;
            }
            count++; //increment no. of consecutive
        }
        if (count == listSize-1){
            p.setCategoryCard(sList[listSize-1]);
            p.setCategory(CATEGORY.STRAIGHT.rank);
        }
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        List<String> wList = new ArrayList<>();
        String[] sList1 = p1.getValueList();
        String[] sList2 = p2.getValueList();
        //reorder the two list for identifying the higher one
        orderValue(sList1);
        orderValue(sList2);
        int diff = VALUE.getValue(sList1[sList1.length - 1]).score - VALUE.getValue(sList2[sList2.length - 1]).score;
        if (diff > 0) {
            wList.add(sList1[sList1.length - 1]);
            return prepareWinner(p1, CATEGORY.STRAIGHT.rank, wList);
        } else {
            if (diff < 0){
                wList.add(sList2[sList2.length - 1]);
                return prepareWinner(p2, CATEGORY.STRAIGHT.rank, wList);
            }
        }
        return null; //Tie if none is bigger
    }
}
