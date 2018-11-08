package com.example.xxx.bakeme;

import java.util.List;

public class Recipe {

    private String name;
    private int servings;
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public Recipe(String sName, int iServings, List<Ingredients> iIngredients, List<Steps> sSteps) {

        name = sName;
        servings = iServings;
        ingredients = iIngredients;
        steps = sSteps;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }
}
