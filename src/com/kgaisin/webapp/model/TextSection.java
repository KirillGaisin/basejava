package com.kgaisin.webapp.model;

public class TextSection extends AbstractSection {
    private String header;
    private String content;

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
    public String toString() {
        return "TextSection{" +
                "header='" + header + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
