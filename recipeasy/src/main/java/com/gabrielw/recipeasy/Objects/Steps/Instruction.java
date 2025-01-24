package com.gabrielw.recipeasy.Objects.Steps;

import java.util.ArrayList;
import java.util.List;

public class Instruction implements Composite {
    private final int stepNumber;
    private final String description;

    public Instruction(int stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }

    @Override
    public int getStepNumber() {
        return stepNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Composite> getValues() {
        List<Composite> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public String toString() {
        return "." + stepNumber + ": " + description;
    }
}