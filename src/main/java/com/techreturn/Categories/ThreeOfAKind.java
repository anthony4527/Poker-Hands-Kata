package com.techreturn.Categories;

import com.techreturn.Categories.ICategory;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfAKind extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        List<String> r = lookForMultiple(p, 3L);
        if (r.size() ==1 ){
            p.setCategoryCard(r.get(0));
            p.setCategory(CATEGORY.THREEAKIND.rank);
        }// set Category if three same cards exist
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        ArrayList<String> wList = new ArrayList<>();
        final String v1 = p1.getCategoryCard(0);
        final String v2 = p2.getCategoryCard(0);

        int score1 = VALUE.getValue(v1).score;
        int score2 = VALUE.getValue(v2).score;

        if (score1 > score2) {
            wList.add(v1);
            return prepareWinner(p1, CATEGORY.THREEAKIND.rank, wList);
        } else if (score1 < score2) {
            wList.add(v2);
            return prepareWinner(p2, CATEGORY.THREEAKIND.rank, wList);
        } else System.out.println ("Error - not possible players have same three-of-a-king");
            //not possible for both players have same three-of-a-king
        return null; //throw exception later on
    }
}
