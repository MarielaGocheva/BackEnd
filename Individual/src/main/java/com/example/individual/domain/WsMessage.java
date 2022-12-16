package com.example.individual.domain;

public class WsMessage {
    private String name;

    public WsMessage() {
    }

    public WsMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
