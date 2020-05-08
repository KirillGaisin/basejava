package com.kgaisin.webapp.model;

import java.util.Arrays;
import java.util.Objects;

public class Position {
    private Link link;
    private Period[] period;

    public Position(Link link, Period... period) {
        this.link = link;
        this.period = period;
    }

    public Link getlink() {
        return link;
    }

    public Period[] getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return link.equals(position.link) &&
                Arrays.equals(period, position.period);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(link);
        result = 31 * result + Arrays.hashCode(period);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Position{");
        sb.append("link=").append(link);
        sb.append(", period=").append(period == null ? "null" : Arrays.asList(period).toString());
        sb.append('}');
        return sb.toString();
    }
}
