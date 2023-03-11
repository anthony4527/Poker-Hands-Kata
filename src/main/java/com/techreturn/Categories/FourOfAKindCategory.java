package com.techreturn.Categories;

import com.techreturn.Categories.HighCard;
import com.techreturn.Categories.ICategory;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.ArrayList;
import java.util.List;

public class FourOfAKindCategory extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        List<String> r = lookForMultiple(p, 4L);
        if (r.size() ==1 ){
            p.setCategoryCard(r.get(0));
            p.setCategory(CATEGORY.FOURAKIND.rank);
        }// set Category if four same cards exist
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {

        return (compareHighestCard(p1,p2, CATEGORY.FOURAKIND.rank));

    }
}
