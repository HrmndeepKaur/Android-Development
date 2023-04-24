package com.example.recipeapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe_Activity extends AppCompatActivity implements NetworkingService.NetworkingListener{
    NetworkingService networkingManger;
    JSON_Service jsonService;
    String recipe_name;
    TextView recipeName;
    TextView cuisineType;
    TextView mealType;
    TextView totaltime;
    TextView ingredientsText;
    TextView CaloriesText;
    ImageView imageView;

    Recipe newRecipe = new Recipe();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepies);

        recipeName = findViewById(R.id.recipeName);
        cuisineType = findViewById(R.id.cuisin_type);
        mealType = findViewById(R.id.mealType);
        totaltime = findViewById(R.id.totalTime);
        ingredientsText = findViewById(R.id.ingredients);
        CaloriesText = findViewById(R.id.calories);
        imageView = findViewById(R.id.recipeImage);

        recipe_name = getIntent().getStringExtra("recipe_name");
        recipeName.setText(recipe_name);

        networkingManger = ((MyAPP)getApplication()).getNetworkingService();
        jsonService = ((MyAPP)getApplication()).getJsonService();
        networkingManger.listener = this;

        networkingManger.getRecipeData(recipe_name);
    }

    @Override
    public void dataListener(String jsonRecipeString){
        Recipe rData = jsonService.getRecipeData(recipe_name, jsonRecipeString);
        cuisineType.setText(rData.getCuisineType());
        mealType.setText(rData.getMealType());
        totaltime.setText(rData.getTime());
        ingredientsText.setText(rData.getIngredients());
        CaloriesText.setText(rData.getCalories());

        networkingManger.getImageData(rData.getImage());
    }

    @Override
    public void imageListener(Bitmap image){
        imageView.setImageBitmap(image);
    }

    public void saveRecipeToDatabase(View view){
        newRecipe.setNewRecipe(recipeName.getText().toString(), ingredientsText.getText().toString(), CaloriesText.getText().toString(), totaltime.getText().toString(), cuisineType.getText().toString(), mealType.getText().toString());
        DatabaseManager.insertRecipeintoDatabase(newRecipe);
        Toast.makeText(this, getString(R.string.recipeSavedtoDatabase), Toast.LENGTH_LONG).show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.reportMenuitem:{
                Intent toFavouriteRecipesActivity = new Intent(this, Favourites_Activity.class);
                startActivity(toFavouriteRecipesActivity);
                break;
            }
            default: {
                Intent toMainActivity = new Intent(this, MainActivity.class);
                startActivity(toMainActivity);
                break;
            }
        }
        return true;
    }
}
