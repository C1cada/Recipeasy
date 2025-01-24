package com.gabrielw.recipeasy.Objects.Steps;

import java.util.ArrayList;
import java.util.List;

public class InstructionContainer implements Composite {
    private final List<Composite> children;
    private final int stepNumber;
    private final String description;

    public InstructionContainer(int stepNumber, String description) {
        children = new ArrayList<>();
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public void add(Composite child) {
        children.add(child);
    }

    public void remove(Composite child) {
        children.remove(child);
    }

    @Override
    public List<Composite> getValues() {
        List<Composite> values = new ArrayList<>();
        for (Composite child : children) {
            values.addAll(child.getValues());
        }
        return values;
    }

    @Override
    public int getStepNumber() {
        return stepNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }
}