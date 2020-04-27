package com.kgaisin.webapp.model;

import java.util.Arrays;

public class Position {
    private Link positionProvider;
    private Period[] period;

    public Position(Link positionProvider, Period... period) {
        this.positionProvider = positionProvider;
        this.period = period;
    }

    public Link getPositionProvider() {
        return positionProvider;
    }

    public Period[] getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionProvider=" + positionProvider +
                ", period=" + Arrays.toString(period) +
                '}';
    }
}
