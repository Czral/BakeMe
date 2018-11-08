package com.example.xxx.bakeme;

public class Ingredients {

    private String measure;
    private String ingredient;
private double quantity;

public Ingredients(double dQuantity, String sMeasure, String sIngredient) {

    quantity = dQuantity;
    measure = sMeasure;
    ingredient = sIngredient;
}

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
