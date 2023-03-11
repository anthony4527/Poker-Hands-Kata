package com.techreturn.Enum;

public enum SUIT {
    CLUB('C',"club"), DIAMOND('D',"diamond"), HEART('H',"heart"), SPADE('S',"spade");

    public final char literal;
    public final String text;
    private SUIT(char literal, String text){
        this.literal = literal;
        this.text = text;
    }

    public static SUIT getValue (char c){
        for (SUIT s: SUIT.values()){
            if (c == s.literal )return s;
        }
        return null;
    }

}
