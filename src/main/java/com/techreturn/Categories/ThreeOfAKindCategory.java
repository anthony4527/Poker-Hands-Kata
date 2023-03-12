package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import com.techreturn.Tools.PokerTools;

import java.util.List;

public class ThreeOfAKindCategory extends PokerTools implements ICategory {
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
        return (compareHighestCard(p1,p2, CATEGORY.THREEAKIND.rank));

    }
}
