package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Recipe_Adapter.recipeClickListener, NetworkingService.NetworkingListener {
    NetworkingService networkingManager;
    ArrayList<Recipe> recipes = new ArrayList<Recipe>(0);
    Recipe_Adapter adaptr;
    JSON_Service jsonService;
    RecyclerView recyclerView;
    DatabaseManager databaseManager = new DatabaseManager();
    TextView mainTitle;
    TextView mainDesc;
    MenuItem searchViewMenuItem;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager.getDatabaseInstance(this);
        networkingManager = ((MyAPP) getApplication()).getNetworkingService();
        networkingManager.listener = this;
        jsonService = ((MyAPP) getApplication()).getJsonService();
        recyclerView = findViewById(R.id.recipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainTitle = findViewById(R.id.Title);
        mainDesc = findViewById(R.id.app_desc);

        adaptr = new Recipe_Adapter(this, recipes);
        recyclerView.setAdapter(adaptr);
        setTitle(getString(R.string.Search));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        inflater.inflate(R.menu.option_menu, menu);
        searchViewMenuItem = menu.findItem(R.id.search);

        searchView = (SearchView) searchViewMenuItem.getActionView();
        String search_s = searchView.getQuery().toString();
        if(!search_s.isEmpty()){
            searchView.setIconified(false);
            searchView.setQuery(search_s, false);
        }
        searchView.setQueryHint(getString(R.string.main_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                networkingManager.searchForRecipes(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() >= 1){
                    networkingManager.searchForRecipes(newText);
                }
                else{
                    recipes = new ArrayList<>(0);
                    adaptr.recipeList = recipes;
                    adaptr.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        Intent FavRecipeActivity = new Intent(this, Favourites_Activity.class);
        startActivity(FavRecipeActivity);
        return true;
    }
    @Override
    public void dataListener(String jsonRecipeString) {
        recipes = jsonService.getRecipesFromJSON(jsonRecipeString);
        if(recipes.size() > 0){
            mainTitle.setText("");
            mainDesc.setText("");
        }
        else{
            mainTitle.setText(R.string.main_title);
            mainDesc.setText(R.string.mainDesc);
        }
        adaptr = new Recipe_Adapter(this, recipes);
        recyclerView.setAdapter(adaptr);
        adaptr.notifyDataSetChanged();
    }

    @Override
    public void imageListener(Bitmap image) {

    }

    @Override
    public void recipeClicked(Recipe selectedRecipe) {
        Intent intent = new Intent(this, Recipe_Activity.class);
        intent.putExtra("recipeName", selectedRecipe.getName());
        startActivity(intent);
    }
}