package edu.utdallas.pages.controllers;

public enum Status {

    SUCCESS("status","success"),
    FAIL("status","fail"),
    TAKEN("status","taken"),
    DENIED("status","denied"),
    INVALID("status","invalid");

    private final String json;

    public String getJson() {
        return json;
    }

    Status(String... status) {
        this.json = JsonUtils.createJson(status);
    }

}
