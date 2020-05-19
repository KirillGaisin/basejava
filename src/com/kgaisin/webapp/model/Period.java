package com.kgaisin.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return dateSince.equals(period.dateSince) &&
                dateUntil.equals(period.dateUntil) &&
                description.equals(period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateSince, dateUntil, description);
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
