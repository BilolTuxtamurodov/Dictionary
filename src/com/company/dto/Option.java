package com.company.dto;

public class Option {
    public char opt;
    public String name;
    public boolean isTrue;

    public Option(String name, boolean isTrue) {
        this.name = name;
        this.isTrue = isTrue;
    }
}
