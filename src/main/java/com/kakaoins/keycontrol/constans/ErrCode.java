package com.kakaoins.keycontrol.constans;

public enum ErrCode {
    /**
     * 에러코드 정의
     */
    // HttpStatus 500
    E0000("000", "예상치 못한 오류가 발생하였습니다."),

    // HttpStatus 400
    E0101("001", ""),

    // HttpStatus 403
    E0301("002", "");


    public final String code;
    public final String errMsg;

    ErrCode(String code, String errMsg){
        this.code = code;
        this.errMsg = errMsg;
    }

    public String getCode(){
        return code;
    }

    public String getErrMsg(){
        return  errMsg;
    }
}
