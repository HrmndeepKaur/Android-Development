package com.example.recipeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Favourites_Activity extends AppCompatActivity implements Favourites_Adapter.favouriteClickListener, DatabaseManager.DatabaseActionListener{
    DatabaseManager databaseManager = new DatabaseManager();
    RecyclerView recyclerView;
    Favourites_Adapter adaptr;
    ArrayList<Recipe> favRecipes = new ArrayList<>(0);
    AlertDialog.Builder builder;
    NetworkingService networkingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        networkingManager = ((MyAPP)getApplication()).getNetworkingService();
        databaseManager.listener = this;

        recyclerView = findViewById(R.id.fav_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptr = new Favourites_Adapter(this, favRecipes);
        recyclerView.setAdapter(adaptr);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        databaseManager.getAllRecipesSaved();
    }

    @Override
    public void favListener(List<Recipe> savedRecipes){
        favRecipes = new ArrayList<Recipe>(savedRecipes);
        adaptr = new Favourites_Adapter(this, favRecipes);
        recyclerView.setAdapter(adaptr);
        adaptr.notifyDataSetChanged();
    }

    @Override
    public void favouriteRecipeClicked(Recipe selectdRecipe) {
        builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle(selectdRecipe.getName());
        builder.setMessage(Html.fromHtml("<p><b>" + "Cuisine Type: " + "</b><br>" + selectdRecipe.getCuisineType() + "</p><p><b>" + "Meal Type: " + "</b><br>" + selectdRecipe.mealType + "</p><p><b>" + "Calories: " + "</b><br>" + selectdRecipe.getCalories() + "</p><p><b>" + "Total Time: " + "</b><br>" + selectdRecipe.getTime() + "</p><p><b>" + "Ingredients: " + "</b><br>" + selectdRecipe.getIngredients() + "</p><br>"));
        builder.setNegativeButton("Full Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent toRecipeActivity = new Intent(getApplicationContext(), Recipe_Activity.class);
                toRecipeActivity.putExtra("recipeName", selectdRecipe.getName());
                startActivity(toRecipeActivity);
            }
        });
        builder.show();
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            databaseManager.deleteRecipeFromDatabase(favRecipes.get(viewHolder.getAdapterPosition()));
            databaseManager.getAllRecipesSaved();
        }
    };
}
