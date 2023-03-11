package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

public class FlushCategory extends HighCard implements ICategory {
    @Override
    public void match(Player p) {
        int i=0;
        char[] suits = p.getSuitList();
        //check if all suit are same then match
        char sample = suits[0];
        do {
            if (suits[i+1] != sample) break;
            i++;
        } while (i< suits.length-1);
        if (i == suits.length-1){p.setCategory(CATEGORY.FLUSH.rank);}
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception {
        return null;
    }
}
