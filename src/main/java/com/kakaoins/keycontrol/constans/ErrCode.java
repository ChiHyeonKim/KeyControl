package com.kakaoins.keycontrol.constans;

public enum ErrCode {
    /**
     * 에러코드 정의
     */
    // HttpStatus 500
    E0000("500", "이미 등록된 KEY 입니다."),

    // HttpStatus 400
    E0101("001", ""),

    // HttpStatus 404
    E0301("404", "KEY가 없습니다.");


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
