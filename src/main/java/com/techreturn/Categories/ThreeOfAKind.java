package com.techreturn.Categories;

import com.techreturn.Categories.ICategory;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

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
        return null;
    }
}
