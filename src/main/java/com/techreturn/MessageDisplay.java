package com.techreturn;

import java.util.Map;

public class MessageDisplay {

    private String name;
    private String cardValue;
    public MessageDisplay (Map<String, String> winner){
        for (String name:winner.keySet()){
            this.name = name;
            this.cardValue = winner.get(name);
        }
        //        Map.Entry<String, String> entry = winner.entrySet();
        /*this.name = entry.getKey();
        this.cardValue = entry.getValue();*/
    }

    public String print(){
        //format output message
        String msg = "";
        if (this.cardValue == "") {msg ="Tie";}
        else {
            msg = this.name + " wins - with high card: "+ VALUE.getValue(this.cardValue).text;
        }
        return msg;
    }
}
