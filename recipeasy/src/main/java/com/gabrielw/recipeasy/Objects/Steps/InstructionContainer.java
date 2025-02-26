package com.gabrielw.recipeasy.Objects.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstructionContainer implements InstructionComposite {
    private final String key;
    private final List<InstructionComposite> values;
    private final int step_number;
    private final String description;

    public InstructionContainer(String key, int step_number, String description) {
        this.values = new ArrayList<>();
        this.step_number = step_number;
        this.description = description;
        this.key = key;
    }

    public InstructionContainer(int step_number, String description) {
        this.values = new ArrayList<>();
        this.step_number = step_number;
        this.description = description;
        this.key = UUID.randomUUID().toString();
    }

    public void add(InstructionComposite child) {
        values.add(child);
    }

    public void remove(InstructionComposite child) {
        values.remove(child);
    }

    @Override
    public List<InstructionComposite> getValues() {
        return values;
    }

    @Override
    public int getStepNumber() {
        return step_number;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return key;
    }
}