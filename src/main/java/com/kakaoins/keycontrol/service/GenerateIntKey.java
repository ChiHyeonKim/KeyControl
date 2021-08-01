package com.kakaoins.keycontrol.service;

/**
 * NUMBER 형태의 KEY를 생성할떄 사용
 */
public class GenerateIntKey {
    int GenerateIntKey(int num){
        double dValue = Math.random();

        int iValue = (int)((dValue + 1 ) * ( Math.pow(10, num-1)));

        return iValue;
    }

}
