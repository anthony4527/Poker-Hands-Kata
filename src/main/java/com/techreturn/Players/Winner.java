package com.techreturn.Players;

import com.techreturn.Players.Player;

public class Winner extends Player {
    private int category=0;
    private String[] winCards = new String[2];
    private char winSuit;
    public Winner(String name, char[] suit, String[] value){
        super(name, suit, value);
    }

    public int getCategory(){
        return this.category;
    }

    public void setCategory(int category){
        this.category = category;
    }
    public String getWinCard(int i){
        return this.winCards[i];
    }

    public void setWinCard(String cardVal, int i){
        this.winCards[i] = cardVal;
    }

    public void setWinSuit(char suit){
        this.winSuit = suit;
    }
    public char getWinSuit() { return this.winSuit; }
}
