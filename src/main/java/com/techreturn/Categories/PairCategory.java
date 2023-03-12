package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import com.techreturn.Tools.PokerTools;

import java.util.*;

import java.util.stream.Collectors;

public class PairCategory extends PokerTools implements ICategory {


    public PairCategory(){ super();}
    @Override
    public void match(Player p) {
        List<String> r = lookForMultiple(p, 2L);
        if (r.size() ==1 ){
             p.setCategoryCard(r.get(0));
             p.setCategory(CATEGORY.PAIR.rank);
        }// set as Pair Category if only card has a pair
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception{

        ArrayList<String> wList = new ArrayList<>();
        final String v1 = p1.getCategoryCard(0);
        final String v2 = p2.getCategoryCard(0);

        int score1 = VALUE.getValue(v1).score;
        int score2 = VALUE.getValue(v2).score;

        if (score1 > score2) {
            wList.add(v1);
            return prepareWinner(p1, CATEGORY.PAIR.rank, wList);
        } else if (score1 < score2) {
            wList.add(v2);
            return prepareWinner(p2, CATEGORY.PAIR.rank, wList);
        }
        // compare high card if players have same pairs
        List<String> s1 = Arrays.stream(p1.getValueList()).filter(s -> !s.equals(v1)).collect(Collectors.toList());
        List<String> s2 = Arrays.stream(p2.getValueList()).filter(s -> !s.equals(v2)).collect(Collectors.toList());

        return findWinnerOfHigherCard( p1, s1, p2, s2);

    }
}
