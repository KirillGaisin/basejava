package com.kgaisin.webapp.model;

import java.util.List;

public class ExperienceSection extends Section {
    private List<Occupation> pastOccupations;

    public ExperienceSection(List<Occupation> pastOccupations) {
        this.pastOccupations = pastOccupations;
    }

    public List<Occupation> getPastOccupations() {
        return pastOccupations;
    }

    @Override
    public String toString() {
        return "ExperienceSection{" +
                "pastOccupations=" + pastOccupations +
                '}';
    }
}
