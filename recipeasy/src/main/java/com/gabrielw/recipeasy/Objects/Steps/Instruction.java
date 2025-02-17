package com.gabrielw.recipeasy.Objects.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Instruction implements InstructionComposite {
    private final String key;
    private final int step_number;
    private final String description;

    public Instruction(String key, int step_umber, String description) {
        this.key = key;
        this.step_number = step_umber;
        this.description = description;
    }

    public Instruction(int step_number, String description) {
        this.step_number = step_number;
        this.description = description;
        this.key = UUID.randomUUID().toString();
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
    public List<InstructionComposite> getValues() {
        List<InstructionComposite> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public String toString() {
        return "." + step_number + ": " + description;
    }

    @Override
    public String getKey() {
        return key;
    }
}