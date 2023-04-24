package com.example.recipeapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSON_Service {
    public ArrayList<Recipe> getRecipesFromJSON(String jsonRecipeString){
        ArrayList<Recipe> recipeList = new ArrayList<>(0);

        try{
            JSONObject jsonObject = new JSONObject(jsonRecipeString);
            JSONArray hitsArray = jsonObject.getJSONArray("hits");

            for(int i = 0; i < hitsArray.length(); i++){
                JSONObject hitsObject = hitsArray.getJSONObject(i);
                JSONObject recipeObject = hitsObject.getJSONObject("recipe");
                String recipeName = recipeObject.getString("label");

                Recipe newRecipe = new Recipe(recipeName);
                recipeList.add(newRecipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Recipe getRecipeData(String recipe_name, String jsonRecipeString) {
        Recipe recipeData = new Recipe();

        try{
            JSONObject jsonObject = new JSONObject(jsonRecipeString);
            JSONArray hitsArray = jsonObject.getJSONArray("hits");
            for(int i = 0; i < hitsArray.length(); i++){
                JSONObject hitsObject = hitsArray.getJSONObject(i);
                JSONObject recipeObject = hitsObject.getJSONObject("recipe");
                String recipeName = recipeObject.getString("label");
                String image = recipeObject.getString("image");

                if(recipeName.equals(recipe_name)){
                    JSONArray ingridentsData = recipeObject.getJSONArray("ingridientsData");
                    String ingredientString = "";
                    for(int  j = 0; j < ingridentsData.length(); j++){
                        ingredientString += ingridentsData.getString(j);
                        if(ingridentsData.length() != j + 1){
                            ingredientString += ", ";
                        }
                    }

                    JSONArray cuisineData = recipeObject.getJSONArray("cuisineData");
                    String cuisineString = "";
                    for (int j = 0; j < cuisineData.length(); j++) {
                        cuisineString += cuisineData.getString(j);
                        if (cuisineData.length() != j + 1) {
                            cuisineString += ", ";
                        }
                    }

                    JSONArray mealData = recipeObject.getJSONArray("mealData");
                    String mealString = "";
                    for (int j = 0; j < mealData.length(); j++) {
                        mealString += mealData.getString(j);
                        if (mealData.length() != j + 1) {
                            mealString += ", ";
                        }
                    }

                    String caloriesData = recipeObject.getString("calories");

                    String totalTimeData = recipeObject.getString("totalTime");

                    recipeData = new Recipe(recipeName, ingredientString, caloriesData, totalTimeData, cuisineString, mealString, image);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeData;
    }
}
