package com.techreturn;

import com.techreturn.Categories.*;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.SUIT;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;
import com.techreturn.View.MessageDisplay;
import java.util.List;
import java.util.ArrayList;

public class PokerHands {
    public final int NumOfPersons = 2;
    public final int NumOfCards =5;
    public final int NumOfTrumpCards = 2; //Maxinum no. of trump cards for ranking

    public String compare(String first, String second) {
        //Main class to call Poker hands Matcher and compare ranking
        Player[] players = new Player[2];

        //list of matchers for ranking categories
        List<ICategory> matchList = new ArrayList<ICategory>();
        ICategory pair = new PairCategory();
        ICategory highCard = new HighCardCategory();
        ICategory twoPairs = new TwoPairsCategory();
        ICategory threeOfAKind = new ThreeOfAKindCategory();
        ICategory straight = new StraightCategory();
        ICategory flush = new FlushCategory();
        ICategory fullHouse = new FullHouseCategory();
        ICategory fourOfAKind = new FourOfAKindCategory();
        ICategory straightFlush = new StraightFlushCategory();

        matchList.add(highCard);    //insert from low categoty to high categories
        matchList.add(pair);
        matchList.add(twoPairs);
        matchList.add(threeOfAKind);
        matchList.add(straight);
        matchList.add(flush);
        matchList.add(fullHouse);
        matchList.add(fourOfAKind);
        matchList.add(straightFlush);

        //Setup players poker hands for comparison
        try {
            players[0] = setUp(first);
            players[1] = setUp(second);
        }catch (Exception e){
            return (e.getMessage());
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
            return("no result identified!!"); //if exception occurs and winner not returned by all compariosn
    }

    Player setUp(String cardsInput) throws Exception{
        //setup the cards of a player and validate the input cards value
        char[] suitList = new char[NumOfCards];
        String[] valueList = new String[NumOfCards];
        int strLen = 0;

        String[] tempInfo = cardsInput.split(" ");
        //extract all suits into suitList
        //extract all values into valueList
        //Validate and throw exception
        if (tempInfo.length> (NumOfCards + 1))
            throw new Exception("??Incorrect number of cards - "+cardsInput);

        for (int j = 0; j < NumOfCards; j++) {
            strLen = tempInfo[j + 1].length();
            suitList[j] = tempInfo[j + 1].charAt(strLen - 1);
            if (SUIT.getValue(suitList[j]) == null)
                throw new Exception("??Invalid suit - "+suitList[j]);
            valueList[j] = tempInfo[j + 1].substring(0, strLen - 1);
            if (VALUE.getValue(valueList[j]) == null)
                throw new Exception("??Invalid card value - "+valueList[j]);
        }
        tempInfo[0] = tempInfo[0].substring(0, tempInfo[0].length() - 1);//name of player
        Player player = new Player(tempInfo[0], suitList, valueList);

        return player;
    }

    private Winner createWinner(Player p) {
        Winner winner = new Winner(p.getName(), p.getSuitList(), p.getValueList());
        winner.setCategory(p.getCategory());  // pair category

        for (int j=0; j< NumOfTrumpCards; j++){
            if (p.getCategoryCard(j)!=""){
                winner.setWinCard(p.getCategoryCard(j), j);
            }
        }
        if ((p.getCategory() == CATEGORY.FLUSH.rank) || (p.getCategory() == CATEGORY.STRAIGHTFLUSH.rank)) {winner.setWinSuit(p.getSuitList()[0]);}
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
