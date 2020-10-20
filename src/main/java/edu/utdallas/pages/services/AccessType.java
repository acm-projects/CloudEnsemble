package edu.utdallas.pages.services;

public enum AccessType {

    VIEW("0"),
    EDIT("1");

    private String id;

    public String getId() {
        return id;
    }

    AccessType(String id) {
        this.id = id;
    }

}
