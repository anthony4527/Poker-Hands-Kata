package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.ArrayList;
import java.util.List;

public class FlushCategory extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        int i=0;
        char[] suits = p.getSuitList();
        //check if all suit are same then match
        char sample = suits[0];
        do {
            if (suits[i+1] != sample) break;
            i++;
        } while (i< suits.length-1);
        if (i == suits.length-1){p.setCategory(CATEGORY.FLUSH.rank);}
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
            return prepareWinner(p1, CATEGORY.HIGHCARD.rank, wList);
        } else {
            if (diff < 0){
                wList.add(sList2[sList2.length - 1]);
                return prepareWinner(p2, CATEGORY.HIGHCARD.rank, wList);
            }
        }
        return null; //Tie if none is bigger

    }
}
