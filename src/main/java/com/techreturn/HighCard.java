package com.techreturn;

import com.techreturn.Enum.VALUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighCard {

    public void orderValue(String[] cList) {
        String temp = "";
        int count = cList.length;
        // bubble sort - start from 1st one, move highest one to the end
        for (int i= 0; i< count;i++) {
            for(int j=0; j < count -i-1; j++ ){
                if (VALUE.getValue(cList[j]).score > VALUE.getValue(cList[j+1]).score ){
                    temp = cList[j];
                    cList[j] = cList[j+1];
                    cList[j+1] = temp;
                }
            }
        }
    }
    public Map<String, String> compareValueList(String p1, List<String> valueList1, String p2, List<String> valueList2) {

        Map<String, String> winner = new HashMap<>();   //entry of winner with name and high card value

        String[] cList1 = valueList1.toArray(new String[valueList1.size()]);
        String[] cList2 = valueList2.toArray(new String[valueList2.size()]);
        //reorder the two list for identifying the higher one
        orderValue(cList1);
        orderValue(cList2);
        //compare the 1st highest, 2nd highest, etc. of each list
        int k =0;
        int len = cList1.length;
        int r = 0;
        while (k< len) {
            r = VALUE.getValue(cList1[len - k-1]).score - VALUE.getValue(cList2[len - k-1]).score ;
            if (r > 0){
                winner.put(p1,cList1[len - k-1]);
                return winner;
            }
            else
            if (r <0) {
                winner.put(p2, cList2[len - k - 1]);
                return winner;
            }
            else k++;   //continue if same
        }
        winner.put("Tie","");
        return winner;
    }
}
