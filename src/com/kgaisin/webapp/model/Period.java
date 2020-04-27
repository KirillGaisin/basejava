package com.kgaisin.webapp.model;

import java.time.YearMonth;

public class Period {
    private YearMonth dateSince;
    private YearMonth dateUntil;
    private TextSection description;

    public Period(YearMonth dateSince, YearMonth dateUntil, TextSection description) {
        this.dateSince = dateSince;
        this.dateUntil = dateUntil;
        this.description = description;
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
        return "Period{" +
                "dateSince=" + dateSince +
                ", dateUntil=" + dateUntil +
                ", description='" + description + '\'' +
                '}';
    }
}
