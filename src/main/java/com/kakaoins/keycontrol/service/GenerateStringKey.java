package com.kakaoins.keycontrol.service;

/**
 * STRING 형태의 KEY를 구현할 떄 사용
 */
public class GenerateStringKey {
    String GenerateStringKey(){
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder();

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {

                // generate numeric
                int myindex
                        = (int) (theAlphaNumericS.length()
                        * Math.random());

                // add the characters
                builder.append(theAlphaNumericS
                        .charAt(myindex));
            }

            //마지막에 '-' 를 안넣기 위해 추가
            if(i<3) builder.append('-');
        }
        return builder.toString();
    }
}
