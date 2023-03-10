package com.techreturn.Categories;

import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Player;
import com.techreturn.Players.Winner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PairCategory extends HighCard implements ICategory {

    public PairCategory(){ super();}
    @Override
    public void match(Player p) {
        /*
        String cardValue="";
        String[] strList = p.getValueList();
        Map<String, Long> group = Arrays.stream(strList).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));
        //if count =2, return entry for player name and card in pair
        for(Map.Entry<String, Long> entry: group.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry.getValue() == 2L) {
                cardValue = entry.getKey();
                p.setCategory(1);   //set Pair Category
                p.setCategoryCard(cardValue);
                break;
            }
        }*/
        List<String> r = lookForPairs(p);
        if (r.size() ==1 ){
             p.setCategoryCard(r.get(0));
             p.setCategory(1);
        }// set as Pair Category if only card has a pair
    }

    public Winner prepareWinner(Player p, int category, List<String> vList){
        Winner winner = new Winner(p.getName(), p.getSuitList(), p.getValueList());
        for (int i =0; i< vList.size(); i++){
            winner.setWinCard(vList.get(i), i);
        }
        winner.setCategory(category);  // pair category
        return winner;
    }

    public List<String> lookForPairs(Player p){
        String cardValue="";
        List<String> result = new ArrayList<>();
        String[] strList = p.getValueList();
        Map<String, Long> group = Arrays.stream(strList).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));
        //if count =2, return entry for player name and card in pair
        for(Map.Entry<String, Long> entry: group.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if (entry.getValue() == 2L) {
                cardValue = entry.getKey();
                result.add(cardValue);
                //p.setCategory(1);   //set Pair Category
                //p.setCategoryCard(cardValue);
            }
        }
        return result;
    }

    @Override
    public Winner rank(Player p1, Player p2) throws Exception{

        final String v1 = p1.getCategoryCard(0);
        final String v2 = p2.getCategoryCard(0);

        int score1 = VALUE.getValue(v1).score;
        int score2 = VALUE.getValue(v2).score;

        if (score1 > score2) {
            Winner winner = new Winner(p1.getName(), p1.getSuitList(), p1.getValueList());
            winner.setWinCard(v1, 0);
            winner.setCategory(1);  // pair category
            return winner;
        } else if (score1 < score2) {
            Winner winner = new Winner(p2.getName(), p2.getSuitList(), p2.getValueList());
            winner.setWinCard(v2,0);
            winner.setCategory(1);  // pair category
            return winner;
        }
        // compare high card if players have same pairs
        List<String> s1 = Arrays.stream(p1.getValueList()).filter(s -> !s.equals(v1)).collect(Collectors.toList());
        List<String> s2 = Arrays.stream(p2.getValueList()).filter(s -> !s.equals(v2)).collect(Collectors.toList());
        Map<String, String> entry = compareValueList(p1.getName(), s1, p2.getName(), s2);

        String name ="";
        String cValue = "";
        for (String pname:entry.keySet()){
            name = pname;
            cValue = entry.get(name);
        }
        if (name.equals(p1.getName())){
            Winner winner = new Winner(p1.getName(), p1.getSuitList(), p1.getValueList());
            winner.setWinCard(cValue, 0);
            winner.setCategory(0);  //High Card category
            return winner;
        }else {
            Winner winner = new Winner(p2.getName(), p2.getSuitList(), p2.getValueList());
            winner.setWinCard(cValue, 0);
            winner.setCategory(0);  //High Card category
            return winner;
        }
    }
}
