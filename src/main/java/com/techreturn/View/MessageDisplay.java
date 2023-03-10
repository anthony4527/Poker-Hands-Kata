package com.techreturn.View;

import com.techreturn.Enum.VALUE;
import com.techreturn.Players.Winner;

public class MessageDisplay {

    final String[] phrase = {
            "high card: #",
            "one pair: #",
            "two pairs: # & #",
            "three-of-a-kind: #"
    };
    private String name;
    private String[] cardValue = new String[2];

    private int category;
    public MessageDisplay (Winner winner){
        this.name = winner.getName();
        this.category = winner.getCategory();
        for (int i =0; i < cardValue.length ; i++){
         cardValue[i] = winner.getWinCard(i);
        }
    }

    public String print(){
        //format output message
        String msg = "";
        if (this.cardValue[0] == "") {msg ="Tie";}
        else {
            String[] parts =  phrase[category].split("#");
            if (parts.length == 1){
                msg = this.name + " wins - with "+ parts[0] + VALUE.getValue(this.cardValue[0]).text;
            } else if (parts.length == 2) {
                msg = this.name + " wins - with "+ parts[0] + VALUE.getValue(this.cardValue[0]).text +
                                        parts[1] + VALUE.getValue(this.cardValue[1]).text;
            }
        }
        return msg;
    }
}
