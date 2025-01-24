package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.List;

public interface Composite {
    public abstract List<Composite> getValues();
    public abstract String getValue();
}