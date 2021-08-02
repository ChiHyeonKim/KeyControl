package com.kakaoins.keycontrol.service;

/**
 * NUMBER 형태의 KEY를 생성할떄 사용
 */
public class GenerateNumberKey {
    String GenerateNumberKey(int num){
        /*
        double dValue = Math.random();

        int iValue = (int)((dValue * 10 ) * ( Math.pow(10, num-1)));

        return iValue;
        */

        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "0123456789";

        //create the StringBuffer
        builder = new StringBuilder();

        for (int i = 0; i < num; i++) {

            // generate numeric
            int myindex
                    = (int) (theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }

}
