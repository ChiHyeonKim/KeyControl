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

    public String getType() {
        return type;
    }

    public int getMin_length() {
        return min_length;
    }

    public Stack<String> getValue() {
        return value;
    }
    public void setValue(String str) {
        this.value.push(str);
    }
}
