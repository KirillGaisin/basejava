package com.kgaisin.webapp.model;

public class Contact {
    private Link link;
    private String text;

    public Contact(Link link) {
        this.link = link;
    }

    public Contact(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "link=" + link +
                ", text='" + text + '\'' +
                '}';
    }
}
