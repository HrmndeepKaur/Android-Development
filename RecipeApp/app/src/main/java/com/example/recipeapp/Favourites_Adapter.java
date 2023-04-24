package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Favourites_Adapter extends RecyclerView.Adapter<Favourites_Adapter.TaskViewHolder>{

    interface favouriteClickListener{
        void favouriteRecipeClicked(Recipe selectdRecipe);
    }
    private Context ct;
    public List<Recipe> favList;
    favouriteClickListener listener;

    public Favourites_Adapter(Context ct, List<Recipe> recipeList){
        this.ct = ct;
        this.favList = recipeList;
        listener = (favouriteClickListener) ct;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ct).inflate(R.layout.recyclerview_recipe, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Recipe t = favList.get(position);
        holder.recipetextView.setText(t.getName());
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

   class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView recipetextView;

        public TaskViewHolder(View view) {
            super(view);

            recipetextView = view.findViewById(R.id.recipe);
            view.setOnClickListener(this);
        }

       @Override
       public void onClick(View view) {
            Recipe recipe = favList.get(getAdapterPosition());
            listener.favouriteRecipeClicked(recipe);
       }
   }
}
