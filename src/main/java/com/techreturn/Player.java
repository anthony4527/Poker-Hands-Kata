package com.techreturn;

public class Player {
    private String name;
    private char[] suitList = new char[5];
    private String[] valueList = new String[5];

    public String getName(){ return this.name;}

    public char[] getSuitList(){
        int len = this.suitList.length;
        char[] suit = new char[len];
        for (int i=0; i < len; i++ ){
            suit[i] = this.suitList[i];
        }
        return suit;
    }
    public String[] getValueList(){
        int len = this.valueList.length;
        String[] vStr = new String[len];
        for (int i=0; i < len; i++ ){
           vStr[i] = this.valueList[i];
        }
        return vStr;
    }


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
