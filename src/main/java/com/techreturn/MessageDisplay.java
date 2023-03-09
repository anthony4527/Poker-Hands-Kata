package com.techreturn;

public class MessageDisplay {

    private final String name;
    private final String cardValue;
    public MessageDisplay (String name, String cardValue){
        this.name = name;
        this.cardValue = cardValue;
    }

    public String print(){
        //format output message
        String msg = name + " wins - with higher card: "+cardValue;
        return msg;
    }
}
