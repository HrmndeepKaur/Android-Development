package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class Recipe_Adapter extends RecyclerView.Adapter<Recipe_Adapter.TasksViewHolder> {
    interface recipeClickListener{
        void recipeClicked(Recipe selectedRecipe);
    }
    private Context recpCont;
    public List<Recipe> recipeList;
    recipeClickListener listener;

    public Recipe_Adapter(Context recpCont, List<Recipe> recipeList){
        this.recpCont = recpCont;
        this.recipeList = recipeList;
        listener = (recipeClickListener) recpCont;
    }


    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(recpCont).inflate(R.layout.recyclerview_recipe, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Recipe r = recipeList.get(position);
        holder.recipe_txt_view.setText(r.getName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

     class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView recipe_txt_view;

        public TasksViewHolder(View itemView) {
            super(itemView);
            recipe_txt_view = itemView.findViewById(R.id.recipe);
            itemView.setOnClickListener(this);
        }
         @Override
         public void onClick(View view) {
            Recipe recipe = recipeList.get(getAdapterPosition());
            listener.recipeClicked(recipe);
         }
     }
}
