package com.kgaisin.webapp.model;

import java.util.List;

public class ExperienceSection extends AbstractSection {
    private List<Position> pastPositions;

    public ExperienceSection(List<Position> pastPositions) {
        this.pastPositions = pastPositions;
    }

    public List<Position> getPastPositions() {
        return pastPositions;
    }

    @Override
    public String toString() {
        return "ExperienceSection{" +
                "pastPositions=" + pastPositions +
                '}';
    }
}
