package edu.utdallas.pages.services;

public enum AccessLevel {

    ONLY_EDIT("0"),
    ANYONE_VIEW("1"),
    ANYONE_EDIT("2"),
    ANYONE_VIEW_SEARCHABLE("3"),
    ANYONE_EDIT_SEARCHABLE("4"),
    CUSTOM("5");

    private String id;

    public String getId() {
        return id;
    }

    AccessLevel(String id) {
        this.id = id;
    }

}
