package com.example.recipeapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {
    static RecipeDatabase database;

    public static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    static Handler handler = new Handler(Looper.getMainLooper());

    interface DatabaseActionListener{
        void favListener(List<Recipe> savedRecipe);
    }

    DatabaseActionListener listener;

    public static void buildDatabaseInstance(Context context){
    database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe-database").build();
    }

    public RecipeDatabase getDatabaseInstance(Context context) {
        if(database == null){
            buildDatabaseInstance(context);
        }
        return database;
    }

    public static void insertRecipeintoDatabase(Recipe newRecipe) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.getRecipeDAO().insertNewRecipe(newRecipe);
            }
        });
    }

    public void deleteRecipeFromDatabase(Recipe recipe) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.getRecipeDAO().deleteRecipe(recipe);
            }
        });
    }

    public void getAllRecipesSaved() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Recipe> savedRecipes = database.getRecipeDAO().getAllSavedRecipes();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.favListener(savedRecipes);
                    }
                });
            }
        });
    }
}



