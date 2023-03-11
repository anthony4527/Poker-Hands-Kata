package com.techreturn.Categories;

import com.techreturn.Categories.HighCard;
import com.techreturn.Categories.ICategory;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

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
        return null;
    }
}
