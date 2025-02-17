package com.gabrielw.recipeasy.Objects.Steps;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface InstructionComposite {
    public abstract List<InstructionComposite> getValues();
    public abstract int getStepNumber();
    public abstract String getDescription();
    public abstract String getKey();

    @JsonCreator
    public static InstructionComposite create(@JsonProperty("key") String key,
                                       @JsonProperty("step_number") int step_number,
                                       @JsonProperty("description") String description,
                                       @JsonProperty("values") List<InstructionComposite> values) {
        System.err.println(values);
        if (values == null){
            if (key == null){
                return new Instruction(step_number, description);
            } else {
                return new Instruction(key, step_number, description);
            }
        } else {
            InstructionContainer con;     
            if (key == null){
                con = new InstructionContainer(step_number, description);
            } else {
                con = new InstructionContainer(key, step_number, description);
            }
            for (InstructionComposite value : values){
                con.add(create(value.getKey(), value.getStepNumber(), value.getDescription(), value.getValues()));
            }
            return con;
        }
    }
}