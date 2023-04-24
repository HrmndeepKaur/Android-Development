package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String Name;
    public String ingredients;
    public String calories;
    public String time;
    public String cuisineType;
    public String mealType;

    @Ignore
    public String image;

    public Recipe(){}

    public Recipe(String Name){
        this.Name = Name;
    }
    public Recipe(String Name, String ingredients, String calories, String time, String cuisineType, String mealType, String image) {
        this.Name = Name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.time = time;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.image = image;
    }
    public String getName() {
        return Name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getMealType() {
        return mealType;
    }

    public String getTime() {
        return time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getCalories() {
        return calories;
    }

    public String getImage() {
        return image;
    }

    public void setNewRecipe(String Name, String ingredients, String calories, String time, String cuisineType, String mealType){
        this.Name = Name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.time = time;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
    }
}
