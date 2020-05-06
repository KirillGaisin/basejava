package com.kgaisin.webapp.model;

import java.util.Arrays;

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
    public String toString() {
        final StringBuffer sb = new StringBuffer("Position{");
        sb.append("link=").append(link);
        sb.append(", period=").append(period == null ? "null" : Arrays.asList(period).toString());
        sb.append('}');
        return sb.toString();
    }
}
