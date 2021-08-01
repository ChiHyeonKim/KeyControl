package com.kakaoins.keycontrol.domain;

import java.util.HashSet;
import java.util.Stack;

public class Key {
    private String key;
    private String description;
    private String type;
    private String generator;
    private int min_length;

    private Stack<String> value = new Stack<>(); //생성된 STRING KEY 값 저장

    private String resultCode;
    private String resultMessage;

    public Key() {
    }

    public Key(String key, String description, String type) {
        this.key = key;
        this.description = description;
        this.type = type;
    }

    public Key(String key, String description, String type, String generator, int min_length) {
        this.key = key;
        this.description = description;
        this.type = type;
        this.generator = generator;
        this.min_length = min_length;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public int getMin_length() {
        return min_length;
    }

    public void setMin_length(int min_length) {
        this.min_length = min_length;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Stack<String> getValue() {
        return value;
    }

    public void setValue(String str) {
        this.value.push(str);
    }


}
