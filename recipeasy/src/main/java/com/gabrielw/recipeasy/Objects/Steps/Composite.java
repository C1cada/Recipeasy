package com.gabrielw.recipeasy.Objects.Steps;

import java.util.List;

public interface Composite {
    public abstract List<Composite> getValues();
    public abstract int getStepNumber();
    public abstract String getDescription();
}