package edu.utdallas.pages;

public enum TagType {
    Genre("1"),
    Artist("2"),
    Instrument("3"),
    Other("4");

    private String id;

    public String getId() {
        return id;
    }

    TagType(String id) {
        this.id = id;
    }
}
