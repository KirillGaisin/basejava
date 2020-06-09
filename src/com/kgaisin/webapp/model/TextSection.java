package com.kgaisin.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private String header;
    private String content;

    public TextSection() {
    }

    public TextSection(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public TextSection(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return header.equals(that.header) &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, content);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "header='" + header + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
