package com.techreturn.Categories;


import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import java.util.List;

public class TwoPairsCategory extends PairCategory implements ICategory {

    @Override
    public void match(Player p) {
        List<String> r = lookForPairs(p);
        if (r.size() == 2 ){
            for (String s: r){ p.setCategoryCard(s);}
            p.setCategory(2);
        }// set as Two Pair Category if two cards have a pair
    }
    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        return null;
    }
}
