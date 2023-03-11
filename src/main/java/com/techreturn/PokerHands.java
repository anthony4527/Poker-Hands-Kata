package com.techreturn;

import com.techreturn.Categories.*;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import com.techreturn.View.MessageDisplay;
import java.util.List;
import java.util.ArrayList;

public class PokerHands {
    public final int NumOfPersons = 2;
    public final int TEMP1 = 2; //Maxinum no. of trump cards for ranking

    public String compare(String first, String second) {

        String cardsInput[] = {first, second};    //keep each player's input cards
        Player[] players = new Player[2];
        char[] suitList = new char[5];
        String[] valueList = new String[5];
        int strLen = 0;
        //list of matchers for categories
        List<ICategory> matchList = new ArrayList<ICategory>();
        ICategory pair = new PairCategory();
        ICategory highCard = new HighCardCategory();
        ICategory twoPairs = new TwoPairsCategory();
        ICategory threeOfAKind = new ThreeOfAKind();
        ICategory straight = new StraightCategory();
        ICategory flush = new FlushCategory();


        matchList.add(highCard);    //insert from low categoty to high categories
        matchList.add(pair);
        matchList.add(twoPairs);
        matchList.add(threeOfAKind);
        matchList.add(straight);
        matchList.add(flush);

        //create players' instance
        String tempInfo[] = new String[6]; //store name and 5 pokers on hand
        //convert each input string to name and the suit and the number
        for (int i = 0; i < NumOfPersons; i++) {
            tempInfo = cardsInput[i].split(" ");
            //extract all suits into suitList
            //extract all values into valueList
            for (int j = 0; j < 5; j++) {
                strLen = tempInfo[j + 1].length();
                suitList[j] = tempInfo[j + 1].charAt(strLen - 1);
                valueList[j] = tempInfo[j + 1].substring(0, strLen - 1);
            }
            tempInfo[0] = tempInfo[0].substring(0, tempInfo[0].length() - 1);
            players[i] = new Player(tempInfo[0], suitList, valueList);
        }

        //call an interface to categoty matcher for each player
        // then compare based on different categories or same categories
        // start matching from higher category to lower category
        for (int i = 0; i < NumOfPersons; i++) {
            // start matching from higher category to lower category
            for (int j=0; j< matchList.size(); j++)
            {
                ICategory c = matchList.get(matchList.size()-j-1);
                c.match(players[i]);
                if (players[i].getCategory() > 0) {
                    break;
                }
            }
        }
        //compare category rank; create winner instance and output message
        if (players[0].getCategory() > players[1].getCategory()) {
            return announce(createWinner(players[0]));
        } else {
            if (players[0].getCategory() < players[1].getCategory()) {
                return announce(createWinner(players[1]));
            }
        }
        // if same category, call the ranking method for same category
        ICategory sameCategory = matchList.get(players[0].getCategory());

        try {
            Winner winner = sameCategory.rank(players[0], players[1]);
            return announce(winner);
        }catch (Exception e){
            System.out.println("Error in comparing score of same category: "+e.getMessage());
        }
            return("no result identified!!");
    }

    private Winner createWinner(Player p) {
        Winner winner = new Winner(p.getName(), p.getSuitList(), p.getValueList());
        winner.setCategory(p.getCategory());  // pair category

        for (int j=0; j< TEMP1; j++){
            if (p.getCategoryCard(j)!=""){
                winner.setWinCard(p.getCategoryCard(j), j);
            }
        }
        if (p.getCategory() == CATEGORY.FLUSH.rank) {winner.setWinSuit(p.getSuitList()[0]);}
        return (winner);
    }
    private String announce(Winner w) {
        if (w == null){
            return "Tie";
        }else
        {
            MessageDisplay msgDisplay = new MessageDisplay(w);
            return msgDisplay.print();
        }

    }
}
