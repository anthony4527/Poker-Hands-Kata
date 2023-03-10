package com.techreturn.Players;

import java.util.ArrayList;

public class Player {
    private String name;
    private char[] suitList = new char[5];
    private String[] valueList = new String[5];
    //set the result of category matching
    private int category = 0;   //default is high card
    private ArrayList<String> categoryCards = new ArrayList<String>();
    //methods
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
    public int getCategory(){
        return this.category;
    }

    public void setCategory(int category){
        this.category = category;
    }

    public void setCategoryCard(String cardValue){
        this.categoryCards.add(cardValue);
    }
    public String getCategoryCard(int i){
        if (i>  this.categoryCards.size()-1) {return null;}
        return this.categoryCards.get(i);   //return null if no more
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
