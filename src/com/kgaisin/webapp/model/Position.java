package com.kgaisin.webapp.model;

import com.kgaisin.webapp.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth dateSince;
    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth dateUntil;
    private String header;
    private String description;

    public Position() {
    }

    public Position(YearMonth dateSince, YearMonth dateUntil, String header, String description) {
        this.dateSince = dateSince;
        this.dateUntil = dateUntil;
        this.header = header;
        this.description = description == null ? "" : description;
    }

    public YearMonth getDateSince() {
        return dateSince;
    }

    public YearMonth getDateUntil() {
        return dateUntil;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(dateSince, position.dateSince) &&
                Objects.equals(dateUntil, position.dateUntil) &&
                Objects.equals(header, position.header) &&
                Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateSince, dateUntil, header, description);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Position{");
        sb.append("dateSince=").append(dateSince);
        sb.append(", dateUntil=").append(dateUntil);
        sb.append(", header='").append(header).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
