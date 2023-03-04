package com.techreturn;

public class PokerHands {

    public final int NumOfPersons = 2;
    public final String[] valueOrder = {"1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public int lookupOrder(String value){
        int j =0;
        while (j < valueOrder.length) {
            if (value.equals(valueOrder[j])) {
                break;
            } else j++;
        }
        return j;
    }
    private String getHighestValue(String[] value){
        // compare each char and set the higher value
        //String highest = value[0];
        String[] sList = new String[value.length];
        for (int j=0; j< value.length;j++ ){
            sList[j] = value[j];
        }
        String temp = "";
        for (int i= 0; i< (sList.length-1);i++) {
            // compare i & i+1,
            //if i is a
            if (lookupOrder(sList[i]) > lookupOrder(sList[i+1]) ){
                temp = sList[i];
                sList[i] = sList[i+1];
                sList[i+1] = temp;
          //      highest = value[i + 1];
            }
        }
        return value[value.length-1];
    }

    private int findHigher(String first, String second){
        System.out.println(first +" "+second);
        if (first.equals(second)){
            return 0;
        }
        String[] strArray = {first, second};
        if (getHighestValue(strArray).equals(first)){
            return 1;
        } else return 2;

    }
    public String compare(String first, String second){

        String cardsInput[] = {first, second};    //keep each player's input cards
        Player[] players = new Player[2];
        char[] suitList = new char[5];
        String[] valueList = new String[5];
        int strLen=0;

        String tempInfo[]= new String[6]; //store name and 5 pokers on hand
        //convert each input string to name and the suit and the number
        //create two arrays - one for suit and one for values
        for (int i = 0; i < NumOfPersons; i++){
            tempInfo = cardsInput[i].split(" ");
            //extract all suits into suitList
            //extract all values into valueList
            for (int j=0; j < 5; j++ ) {
                //
                strLen = tempInfo[j + 1].length();
                suitList[j] = tempInfo[j + 1].charAt(strLen - 1);
                valueList[j] = tempInfo[j + 1].substring(0, strLen - 1);
            }
            tempInfo[0] = tempInfo[0].substring(0, tempInfo[0].length() - 1);

            players[i] = new Player(tempInfo[0], suitList, valueList);

            //compare this player with previous player based on card value
            if (i==1){
                // if (i higher than(i-1))is true;return i,
                // if (i less than i=1 is false; return i-1
                // if they are same,return none
                // compareResult (1st, 2nd) return 1for 1st higher, 2 for 2nd higher, 0 for same
                 int compareResult = findHigher(getHighestValue(players[i].getValueList()), getHighestValue(players[i-1].getValueList()));
                 switch (compareResult) {
                    case 0:
                        return "";// indicate none is higher
                    case 1:
                        return players[i].getName().toLowerCase();
                    case 2:
                        return players[i-1].getName().toLowerCase();
                }
            }
        }
        return "";
    }
}
