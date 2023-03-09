package com.techreturn;

public enum VALUE {
        ONE("2",1,"2"),TWO("3",2,"3"), FOUR("4",3,"4"),
        FIVE("5",4,"5"),SIX("6",5,"6"),SEVEN("7",6,"7"),
        EIGHT("8",7,"8"), NINE("9",8,"9"),TEN("T",9,"10"),
        JACK("J",10,"Jack"),QUEEN("Q",11,"Queen"),KING("K",12,"King"),
        ACE("A",13,"Ace");

        String value;
        int score;
        String text;
        private VALUE(String value, int score, String text){
            this.value = value;
            this.score = score;
            this.text = text;
        }

        public static VALUE getValue (String value){
            for (VALUE v: VALUE.values()){
                if (value.equals(v.value) ){
                    return v;
                }
            }
            return null;
        }
}
