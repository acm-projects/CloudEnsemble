package edu.utdallas.pages.services;

public enum AccessorType {

    USER("0"),
    BAND("1");

    private final String id;

    public String getId() {
        return id;
    }

    AccessorType(String id) {
        this.id = id;
    }

}
