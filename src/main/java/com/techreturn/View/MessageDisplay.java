package com.techreturn.View;

import com.techreturn.Enum.SUIT;
import com.techreturn.Enum.VALUE;
import com.techreturn.Enum.CATEGORY;
import com.techreturn.Players.Winner;

public class MessageDisplay {

    final String[] phrase = {
            "high card: #",
            "one pair: #",
            "two pairs: # & #",
            "three-of-a-kind: #",
            "straight: #",
            "flush: #",
            "full house: # over #",
            "four-of-a-kind: #",
            "straight flush: # #"
    };
    private String name;
    private String[] cardValue = new String[2];
    private char winSuit;

    private int category;
    public MessageDisplay (Winner winner){
        this.name = winner.getName();
        this.category = winner.getCategory();
        for (int i =0; i < cardValue.length ; i++){
         cardValue[i] = winner.getWinCard(i);
        }
        this.winSuit = winner.getWinSuit();
    }

    public String print(){
        //format output message
        String msg = "";
        if (this.cardValue[0] == "") {msg ="Tie";}
        else {
            String[] parts =  phrase[category].split("#");
            if (parts.length == 1){
                if (category == CATEGORY.FLUSH.rank){
                    msg = this.name + " wins - with "+ parts[0] + SUIT.getValue(this.winSuit).text;
                }else   msg = this.name + " wins - with "+ parts[0] + VALUE.getValue(this.cardValue[0]).text;

            } else if (parts.length == 2) {
                if (category == CATEGORY.STRAIGHTFLUSH.rank){
                    msg = this.name + " wins - with "+ parts[0] +VALUE.getValue(this.cardValue[0]).text +
                            parts[1]+ SUIT.getValue(this.winSuit).text;
                }else
                msg = this.name + " wins - with "+ parts[0] + VALUE.getValue(this.cardValue[0]).text +
                                        parts[1] + VALUE.getValue(this.cardValue[1]).text;
            }
        }
        return msg;
    }
}
