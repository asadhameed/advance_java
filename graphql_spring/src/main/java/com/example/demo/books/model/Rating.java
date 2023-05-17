package com.example.demo.books.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    FIVE_STARS("*****"),
    Four_STARS("****"),
    THREE_STARS("***"),
    TWO_STARS("**"),
    ONE_STARS("*");

    private String star;
    Rating(String star){
        this.star =star;
    }

    @JsonValue
    public String getStar(){
        return this.star;
    }
}
