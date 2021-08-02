package com.kakaoins.keycontrol.dto;

public class GenerateKeyDTO {
    private String key;
    private String description;
    private String type;
    private String generator;
    private int min_length;

    public GenerateKeyDTO() {
    }

    public GenerateKeyDTO(String key, String description, String type) {
        this.key = key;
        this.description = description;
        this.type = type;
    }

    public GenerateKeyDTO(String key, String description, String type, String generator, int min_length) {
        this.key = key;
        this.description = description;
        this.type = type;
        this.generator = generator;
        this.min_length = min_length;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getGenerator() {
        return generator;
    }

    public int getMin_length() {
        return min_length;
    }
}
