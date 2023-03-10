package com.techreturn.Categories;

import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

public interface ICategory {
    public void match(Player player); //method to set highest cards if play's hand match a category

    public Winner rank(Player p1, Player p2) throws Exception;    //compare rank if both same category, return the Winner
}
