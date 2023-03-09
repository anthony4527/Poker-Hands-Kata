package com.techreturn;

import java.util.Map;

public class MessageDisplay {

    final String[] phrase = {
            "high card: ",
            "one pair: "};
    private String name;
    private String cardValue;

    private int category;
    public MessageDisplay (Map<String, String> winner, int category){
        for (String name:winner.keySet()){
            this.name = name;
            this.cardValue = winner.get(name);
        }
        this.category= category;
    }

    public String print(){
        //format output message
        String msg = "";
        if (this.cardValue == "") {msg ="Tie";}
        else {
            msg = this.name + " wins - with "+ phrase[category] + VALUE.getValue(this.cardValue).text;
        }
        return msg;
    }
}
