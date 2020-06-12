package com.kgaisin.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    private Link link;
    private List<Period> period;

    public Position() {
    }

    public Position(Link link, Period... period) {
        this.link = link;
        this.period = Arrays.asList(period);
    }

    public Link getLink() {
        return link;
    }

    public List<Period> getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(link, position.link) &&
                Objects.equals(period, position.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, period);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Position{");
        sb.append("link=").append(link);
        sb.append(", period=").append(period);
        sb.append('}');
        return sb.toString();
    }
}
