package com.kgaisin.webapp.model;

public class Contact {
    private Link linkContact;
    private String stringContact;

    public Contact(Link linkContact) {
        this.linkContact = linkContact;
    }

    public Contact(String stringContact) {
        this.stringContact = stringContact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "linkContact=" + linkContact +
                ", stringContact='" + stringContact + '\'' +
                '}';
    }
}
