package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HighCard {

//    public final int enumHighCard = 0;
    public void orderValue(String[] cList) {
        String temp = "";
        int count = cList.length;
        // bubble sort - start from 1st one, move highest one to the end
        for (int i= 0; i< count;i++) {
            for(int j=0; j < count -i-1; j++ ){
                if (VALUE.getValue(cList[j]).score > VALUE.getValue(cList[j+1]).score ){
                    temp = cList[j];
                    cList[j] = cList[j+1];
                    cList[j+1] = temp;
                }
            }
        }
    }
    public Map<String, String> compareValueList(String p1, List<String> valueList1, String p2, List<String> valueList2) {

        Map<String, String> winner = new HashMap<>();   //entry of winner with name and high card value

        String[] cList1 = valueList1.toArray(new String[valueList1.size()]);
        String[] cList2 = valueList2.toArray(new String[valueList2.size()]);
        //reorder the two list for identifying the higher one
        orderValue(cList1);
        orderValue(cList2);
        //compare the 1st highest, 2nd highest, etc. of each list
        int k =0;
        int len = cList1.length;
        int r = 0;
        while (k< len) {
            r = VALUE.getValue(cList1[len - k-1]).score - VALUE.getValue(cList2[len - k-1]).score ;
            if (r > 0){
                winner.put(p1,cList1[len - k-1]);
                return winner;
            }
            else
            if (r <0) {
                winner.put(p2, cList2[len - k - 1]);
                return winner;
            }
            else k++;   //continue if same
        }
        winner.put("Tie","");
        return winner;
    }

    public Winner prepareWinner(Player p, int category, List<String> vList){
        Winner winner = new Winner(p.getName(), p.getSuitList(), p.getValueList());
        for (int i =0; i< vList.size(); i++){
            winner.setWinCard(vList.get(i), i);
        }
        winner.setCategory(category);  // pair category
        if ((category ==CATEGORY.FLUSH.rank) || (category == CATEGORY.STRAIGHTFLUSH.rank))
                winner.setWinSuit(p.getSuitList()[0]);
        return winner;
    }

    public Winner findWinnerOfHigherCard (Player p1, List<String> s1, Player p2, List<String> s2) {

        Map<String, String> entry = compareValueList (p1.getName(), s1, p2.getName(), s2);

        String name ="";
        String cValue = "";
        ArrayList<String> wList = new ArrayList<>();
        for (String pname : entry.keySet()) {
            name = pname;
            cValue = entry.get(name);
            wList.add(cValue);
        }
        if (name.equals(p1.getName())) {
            return prepareWinner(p1, CATEGORY.HIGHCARD.rank, wList); // rank by high card
        } else if (name.equals(p2.getName())) {
            return prepareWinner(p2, CATEGORY.HIGHCARD.rank, wList);
        }
        return null; //null means no winner i.e. a Tie

    }

    public List<String> lookForMultiple(Player p, long count){
        String cardValue="";
        List<String> result = new ArrayList<>();
        String[] strList = p.getValueList();
        Map<String, Long> group = Arrays.stream(strList).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));
        //if count =2, return entry for player name and card in pair
        for(Map.Entry<String, Long> entry: group.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry.getValue() == count) {
                cardValue = entry.getKey();
                result.add(cardValue);
            }
        }
        return result;
    }

    public Winner compareHighestCard(Player p1, Player p2, int category) throws Exception {
        ArrayList<String> wList = new ArrayList<>();
        final String v1 = p1.getCategoryCard(0);    //the highest card is placed in the 1st entry of trump cards
        final String v2 = p2.getCategoryCard(0);

        int score1 = VALUE.getValue(v1).score;
        int score2 = VALUE.getValue(v2).score;

        if (score1 > score2) {
            wList.add(v1);
            return prepareWinner(p1, category, wList);
        } else if (score1 < score2) {
            wList.add(v2);
            return prepareWinner(p2, category, wList);
        } else //System.out.println ("Unexpected - both players have same highest card");
        //not possible for both players have same three-of-a-king
        return null; //throw exception later on
    }
}
