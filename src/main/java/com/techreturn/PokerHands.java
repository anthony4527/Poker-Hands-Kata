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

    private void orderValue(String[] cList) {
        String temp = "";
        int count = cList.length;
        // bubble sort - start from 1st one, move highest one to the end
        for (int i= 0; i< count;i++) {
            for(int j=0; j < count -i-1; j++ ){
                if (lookupOrder(cList[j]) > lookupOrder(cList[j+1]) ){
                    temp = cList[j];
                    cList[j] = cList[j+1];
                    cList[j+1] = temp;
                }
            }
        }
    }


    private int compareValueList(String[] valueList1, String[] valueList2) {

        String[] cList1 = new String[valueList1.length];
        for (int i=0; i< valueList1.length; i++){ cList1[i]= valueList1[i];}

        String[] cList2 = new String[valueList2.length];
        for (int i=0; i< valueList2.length; i++){ cList2[i]= valueList2[i];}
        //reorder the two list for identifying the higher one
        orderValue(cList1);
        orderValue(cList2);
        //compare the 1st highest, 2nd highest, etc. of each list
        int k =0;
        int len = cList1.length;
        int r = 0;
        while (k< len) {
            r = lookupOrder(cList1[len - k-1]) - lookupOrder(cList2[len - k-1]);
            if (r > 0){return 1;}
            else
            if (r <0) {return 2;}
            else k++;   //continue if same
        }
        return 0;
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
                //compare 1st and 2nd,by order of each highest,2nd highest, etc until a higher between two is identified

                int compareResult = compareValueList (players[i].getValueList(), players[i-1].getValueList());
                switch (compareResult) {
                    case 0:
                        return "Tie";// indicate none is higher
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
