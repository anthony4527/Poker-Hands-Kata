package com.techreturn.Enum;

public enum CATEGORY {

    HIGHCARD(0), PAIR(1), TWOPAIRS(2), THREEAKIND(3), STRAIGHT(4), FLUSH(5), FULLHOUSE(6), FOURAKIND(7), STRAIGHTFLUSH(8);
    public final int rank;
    private CATEGORY(int rank){
        this.rank = rank;
    }
}
