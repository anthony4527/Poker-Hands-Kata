package com.techreturn;

public class Player {
    private String name;
    private char[] suitList = new char[5];
    private String[] valueList = new String[5];

    public String getName(){ return this.name;}

    public char[] getSuitList(){ return this.suitList;}
    public String[] getValueList(){ return this.valueList;}


    public Player(String name, char[] suitList, String[] valueList){
        this.name = name;
        for (int i=0; i< suitList.length;i++){
            this.suitList[i] = suitList[i];
        }
        for (int j=0; j< valueList.length;j++){
            this.valueList[j] = valueList[j];
        }
    }

}
