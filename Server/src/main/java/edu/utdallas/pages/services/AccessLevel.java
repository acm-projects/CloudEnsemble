package edu.utdallas.pages.services;

public enum AccessLevel {

    RESTRICTED("0"),
    VIEW("1"),
    EDIT("2");

    private final String id;

    public String getId() {
        return id;
    }

    AccessLevel(String id) {
        this.id = id;
    }

}
