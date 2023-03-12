package com.techreturn.Categories;

import com.techreturn.Tools.PokerTools;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

public class StraightFlushCategory extends PokerTools implements ICategory {

    @Override
    public void match(Player p) {
        String[] sList = p.getValueList();
        char[] suits = p.getSuitList();
        //check if all suit are same then match
        char sample = suits[0];

        orderValue(sList);
        int listSize = sList.length;
        int count =0;
        //card lists - if card value is sequential  & suit list - if same as sample
        //if above meet for 5 counts, then a straightflush
        for (int i=0; i<listSize-1; i++){
            if ((VALUE.getValue(sList[listSize-1-i]).score - VALUE.getValue(sList[listSize-2-i]).score) != 1){
                break;
            }
            if (suits[listSize-1-i] != sample) break;
            count++; //increment no. of consecutive
        }

        if (count == listSize-1){
            p.setCategoryCard(sList[listSize-1]);
            p.setCategory(CATEGORY.STRAIGHTFLUSH.rank);
        }
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        return (compareHighestCard(p1,p2, CATEGORY.STRAIGHTFLUSH.rank));

    }
}
