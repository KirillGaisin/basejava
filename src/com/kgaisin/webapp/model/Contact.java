package com.kgaisin.webapp.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(link, contact.link) &&
                Objects.equals(text, contact.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, text);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "link=" + link +
                ", text='" + text + '\'' +
                '}';
    }
}
