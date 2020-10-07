package edu.utdallas.pages.controllers;

import edu.utdallas.pages.utils.JsonUtils;

public enum Status {

    SUCCESS("status","success"),
    FAIL("status","fail"),
    TAKEN("status","taken"),
    DENIED("status","denied");

    private final String json;

    public String getJson() {
        return json;
    }

    Status(String... status) {
        this.json = JsonUtils.createJson(status);
    }
}
