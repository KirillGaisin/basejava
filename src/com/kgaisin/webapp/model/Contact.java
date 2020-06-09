package com.kgaisin.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link link;

    public Contact() {
    }

    public Contact(Link link) {
        this.link = link;
    }

    public Link getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(link, contact.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contact{");
        sb.append("link=").append(link);
        sb.append('}');
        return sb.toString();
    }
}
