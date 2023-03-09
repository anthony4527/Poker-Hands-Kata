package com.techreturn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;


public class PokerHands {
    public final int NumOfPersons = 2;

    private void orderValue(String[] cList) {
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


    private Map<String, String> compareValueList(String p1, List<String> valueList1, String p2, List<String> valueList2) {

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

    private Map<String, String> compareHighCards(Player p1, Player p2){
        return compareValueList (p1.getName(), Arrays.asList(p1.getValueList()), p2.getName(),
                                                Arrays.asList(p2.getValueList()));
    }

    public String compare(String first, String second){

        String cardsInput[] = {first, second};    //keep each player's input cards
        Player[] players = new Player[2];
        char[] suitList = new char[5];
        String[] valueList = new String[5];
        int strLen=0;
        int category[] = {0,1};

        String tempInfo[]= new String[6]; //store name and 5 pokers on hand
        //convert each input string to name and the suit and the number
        for (int i = 0; i < NumOfPersons; i++){
            tempInfo = cardsInput[i].split(" ");
            //extract all suits into suitList
            //extract all values into valueList
            for (int j=0; j < 5; j++ ) {

                strLen = tempInfo[j + 1].length();
                suitList[j] = tempInfo[j + 1].charAt(strLen - 1);
                valueList[j] = tempInfo[j + 1].substring(0, strLen - 1);
            }
            tempInfo[0] = tempInfo[0].substring(0, tempInfo[0].length() - 1);

            players[i] = new Player(tempInfo[0], suitList, valueList);
        }
        //add pattern check first  i.e. two in a pair; straight; full house; three in a pair; flush
        for (int j=0; j < NumOfPersons -1 ; j++){
            //identify each hand category
            //return the player which higher category
            //if same category, compare by High Card rule
            Map<String,String> pair1 = findPair(players[j]);
            Map<String,String> pair2 = findPair(players[j+1]);

            if ((pair1.size()!=0) && (pair2.size()==0)){
                MessageDisplay msgDisplay = new MessageDisplay(pair1, category[1]);
                return msgDisplay.print();
                //return players[j].getName().toLowerCase();
            }else {
                if ((pair1.size()==0) && (pair2.size()!=0) ){
//                    return players[j+1].getName().toLowerCase();
                    MessageDisplay msgDisplay = new MessageDisplay(pair1,category[1]);
                    return msgDisplay.print();
                } else {
                    if ((pair1.size()!=0) && (pair2.size()!=0)){
                        Map<String, String> winner =comparePairs(players[j],players[j+1]);
                        MessageDisplay msgDisplay = new MessageDisplay(winner,category[1]);
                        return msgDisplay.print();
                        //return comparePairs(players[j],players[j+1]); //return name of the player with higher score
                    } else {
                        Map<String,String> winner = compareHighCards(players[j],players[j+1]);
                        MessageDisplay msgDisplay = new MessageDisplay(winner,category[0]);
                        return msgDisplay.print();
                    }
                }
            }

        }
        return "";
    }

    private Map<String, String> findPair(Player p){
        String cardValue="";
        String[] strList = p.getValueList();
         Map<String, Long> group = Arrays.stream(strList).collect(Collectors.groupingBy(
                 Function.identity(), Collectors.counting()));
        //if count =2, return entry for player name and card in pair
        for(Entry<String, Long> entry: group.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry.getValue() == 2L) {
                cardValue = entry.getKey();
                break;
            }
        }
        Map<String, String> pair = new HashMap<>();
        if (!cardValue.equals("")){ //insert card in pair to map entry and return
            pair.put(p.getName(),cardValue);
        }
        // if pair not found, return null
        return pair;
    }

    private Map<String, String> comparePairs(Player p1, Player p2) {
        //String value1 = "";
        //String value2 = "";
        final String v1;
        final String v2;
        //Map<String,String> winner = new HashMap<>();

        Map<String, String> pair1 = findPair(p1);
        Map<String, String> pair2 = findPair(p2);
        // value1 = VALUE.getValue();

        v1 = pair1.get(p1.getName());
        v2 = pair2.get(p1.getName());

        int score1 = VALUE.getValue(v1).score;
        int score2 = VALUE.getValue(v2).score;

        if (score1 > score2) {
            return pair1;
        } else if (score1 < score2) {
            return pair2;
        }
        // compare high card if players have same pairs
        List<String> s1 = Arrays.stream(p1.getValueList()).filter(s -> !s.equals(v1)).collect(Collectors.toList());
        List<String> s2 = Arrays.stream(p2.getValueList()).filter(s -> !s.equals(v2)).collect(Collectors.toList());
        Map<String, String> winner = compareValueList(p1.getName(), s1, p2.getName(), s2);

        return winner;
    }
/*
        Map<String, Long> group1 = Arrays.stream(p1.getValueList()).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));
        Map<String, Long> group2 = Arrays.stream(p2.getValueList()).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        for(Entry<String, Long> entry1: group1.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry1.getValue() == 2) {
                value1 = entry1.getKey();
                break;
            }
        }

        for(Entry<String, Long> entry2: group2.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry2.getValue() == 2) {
                value2 = entry2.getKey();
                break;
            }
        }
        int diff = VALUE.getValue(value1).score - VALUE.getValue(value2).score;
        v1 = value1;
        v2 = value2;
        if (diff >0){ return p1.getName().toLowerCase();}
            else if (diff <0 ){return p2.getName().toLowerCase();}
            else { // same pair, then remove pair and compare value
                List<String> s1 = Arrays.stream(p1.getValueList()).filter(s -> !s.equals(v1)).collect(Collectors.toList() );
                List<String> s2 = Arrays.stream(p2.getValueList()).filter(s -> !s.equals(v2)).collect(Collectors.toList() );
                Map<String, String> winner = compareValueList (p1.getName(), s1, p2.getName(), s2);
                return "Tie";*/

                /*
                switch (cp) {
                    case 0:
                        return "Tie".toLowerCase();// indicate none is higher
                    case 1:
                        return p1.getName().toLowerCase();
                    case 2:
                        return p2.getName().toLowerCase();
                }
            }*/

        //return "";

}
