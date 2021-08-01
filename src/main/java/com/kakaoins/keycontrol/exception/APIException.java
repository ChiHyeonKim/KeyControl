package com.kakaoins.keycontrol.exception;

public class APIException extends RuntimeException{
    /**
     * API 관련 오류 정의
     */
    private final String errCode;

    public APIException(String message, String errCode) {
        super(message, null, false, false);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}
