package edu.utdallas.pages.implementations;

public enum Reserved {

    Anonymous("[Anonymous]");

    private final String text;

    public String getText() {
        return text;
    }

    Reserved(String text) {
        this.text = text;
    }
}
