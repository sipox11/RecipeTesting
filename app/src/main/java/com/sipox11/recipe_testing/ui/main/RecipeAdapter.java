package com.sipox11.recipe_testing.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.RecipeStore;
import com.sipox11.recipe_testing.data.model.Recipe;
import com.sipox11.recipe_testing.ui.recipe.RecipeActivity;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final RecipeStore store;

    public RecipeAdapter(RecipeStore store) {
        this.store = store;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        final Recipe recipe = store.recipes.get(position);
        holder.textView.setText(recipe.title);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.textView.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra(RecipeActivity.KEY_ID, recipe.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return store.recipes.size();
    }
}
