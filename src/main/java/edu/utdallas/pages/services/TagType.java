package edu.utdallas.pages.services;

public enum TagType {
    GENRE("1"),
    ARTIST("2"),
    INSTRUMENT("3"),
    OTHER("4");

    private String id;

    public String getId() {
        return id;
    }

    TagType(String id) {
        this.id = id;
    }
}
