package com.kgaisin.webapp.model;

import java.util.List;
import java.util.Objects;

public class ExperienceSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<Position> pastPositions;

    public ExperienceSection(List<Position> pastPositions) {
        this.pastPositions = pastPositions;
    }

    public List<Position> getPastPositions() {
        return pastPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceSection that = (ExperienceSection) o;
        return pastPositions.equals(that.pastPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pastPositions);
    }

    @Override
    public String toString() {
        return "ExperienceSection{" +
                "pastPositions=" + pastPositions +
                '}';
    }
}
