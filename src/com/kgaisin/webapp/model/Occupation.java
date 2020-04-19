package com.kgaisin.webapp.model;

import java.time.YearMonth;

public class Occupation {
    private Link occupationProvider;
    private YearMonth dateSince;
    private YearMonth dateUntil;
    private TextSection description;

    public Occupation(Link occupationProvider, YearMonth dateSince, YearMonth dateUntil, TextSection description) {
        this.occupationProvider = occupationProvider;
        this.dateSince = dateSince;
        this.dateUntil = dateUntil;
        this.description = description;
    }

    public Link getOccupationProvider() {
        return occupationProvider;
    }

    public YearMonth getDateSince() {
        return dateSince;
    }

    public YearMonth getDateUntil() {
        return dateUntil;
    }

    public TextSection getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Occupation{" +
                "occupationProvider=" + occupationProvider +
                ", dateSince=" + dateSince +
                ", dateUntil=" + dateUntil +
                ", description=" + description +
                '}';
    }
}
