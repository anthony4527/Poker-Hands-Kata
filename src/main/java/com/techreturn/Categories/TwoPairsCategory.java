package com.techreturn.Categories;


import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.*;
import java.util.stream.Collectors;

public class TwoPairsCategory extends PairCategory implements ICategory {


    @Override
    public void match(Player p) {
        List<String> r = lookForMultiple(p, 2L);
        if (r.size() == 2) {
            for (String s : r) {
                p.setCategoryCard(s);
            }
            p.setCategory(CATEGORY.TWOPAIRS.rank);
        }// set as Two Pair Category if two cards have a pair
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {

        String[] v1 = {p1.getCategoryCard(0), p1.getCategoryCard(1)};
        String[] v2 = {p2.getCategoryCard(0), p2.getCategoryCard(1)};
        String[] s1, s2;

        if (VALUE.getValue(v1[0]).score > VALUE.getValue(v1[1]).score) {
            s1 = new String[]{v1[0], v1[1]};
        } else {
            s1 = new String[]{v1[1], v1[0]};
        }

        if (VALUE.getValue(v2[0]).score > VALUE.getValue(v2[1]).score) {
            s2 = new String[]{v2[0], v2[1]};
        } else {
            s2 = new String[]{v2[1], v2[0]};
        }

        //iterate compare each pair of two player, until a higher pair is found
        for (int j = 0; j < 2; j++) {
            if (VALUE.getValue(s1[j]).score > VALUE.getValue(s2[j]).score) {
                return prepareWinner(p1, CATEGORY.TWOPAIRS.rank, Arrays.asList(s1));    //set winner as p1 with winning category as Two pairs
            }
            if (VALUE.getValue(s1[j]).score < VALUE.getValue(s2[j]).score) {
                return prepareWinner(p2, CATEGORY.TWOPAIRS.rank, Arrays.asList(s2));    //set winner as p1 with winning category as Two pairs
            }
        }
        // compare high card if players have same pairs
        List<String> sl1 = Arrays.stream(p1.getValueList()).filter(s -> !s.equals(v1[0]) && !s.equals(v1[1])).collect(Collectors.toList());
        List<String> sl2 = Arrays.stream(p2.getValueList()).filter(s -> !s.equals(v2[0]) && !s.equals(v2[1])).collect(Collectors.toList());

        return findWinnerOfHigherCard( p1, sl1, p2, sl2);

    }
}
