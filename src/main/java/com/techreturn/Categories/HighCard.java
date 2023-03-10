package com.techreturn.Categories;

import com.techreturn.Enum.CATEGORY;
import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.*;

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
}
