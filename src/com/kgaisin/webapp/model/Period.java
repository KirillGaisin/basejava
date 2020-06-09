package com.kgaisin.webapp.model;

import com.kgaisin.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateSince;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateUntil;
    private TextSection description;

    public Period() {
    }

    public Period(LocalDate dateSince, LocalDate dateUntil, TextSection description) {
        this.dateSince = dateSince;
        this.dateUntil = dateUntil;
        this.description = description;
    }

    public LocalDate getDateSince() {
        return dateSince;
    }

    public LocalDate getDateUntil() {
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
