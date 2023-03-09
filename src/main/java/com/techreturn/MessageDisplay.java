package com.techreturn;

import java.util.Map;

public class MessageDisplay {

    final String[] phrase = {
            "high card: ",
            "one pair: "};
    private String name;
    private String[] cardValue = new String[2];

    private int category;
    public MessageDisplay (Winner winner){
        /*
        for (String name:winner.keySet()){
            this.name = name;
            this.cardValue = winner.get(name);
        }
        this.category= category;*/
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
            msg = this.name + " wins - with "+ phrase[category] + VALUE.getValue(this.cardValue[0]).text;
        }
        return msg;
    }
}
