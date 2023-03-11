package com.techreturn.Categories;

import com.techreturn.Categories.HighCard;
import com.techreturn.Categories.ICategory;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.ArrayList;
import java.util.List;

public class FullHouseCategory extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        List<String> match3 = lookForMultiple(p, 3L);
        List<String> match2 = lookForMultiple(p, 2L);
        if ((match3.size() ==1 ) && (match2.size() ==1 )){
            //both 3 in a kind and a pair exist then set as FullHouse category
            p.setCategoryCard(match3.get(0));
            p.setCategoryCard(match2.get(0));
            p.setCategory(CATEGORY.FULLHOUSE.rank);
        }
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        List<String> wList = new ArrayList<>();
        String[] matchCard = new String[2];

        List<String> p1card = lookForMultiple(p1, 3L);
        matchCard[0] =p1card.get(0);
        List<String> p2card = lookForMultiple(p2, 3L);
        matchCard[1] = p2card.get(0);

        int diff = VALUE.getValue(matchCard[0]).score - VALUE.getValue(matchCard[1]).score;
        if (diff > 0) {
            wList.add(matchCard[0]);
            String minorCard = lookForMultiple(p1, 2L).get(0);
            wList.add(minorCard);
            return prepareWinner(p1, CATEGORY.FULLHOUSE.rank, wList);
        } else if (diff <0) {
            wList.add(matchCard[1]);
            String minorCard = lookForMultiple(p2, 2L).get(0);
            wList.add(minorCard);
            return prepareWinner(p2, CATEGORY.FULLHOUSE.rank, wList);
             }
        else return null; //Tie if none is bigger

    }
}
