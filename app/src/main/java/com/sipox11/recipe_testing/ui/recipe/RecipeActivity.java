package com.sipox11.recipe_testing.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.RecipeStore;
import com.sipox11.recipe_testing.data.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Get ui elements
        TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);

        // Create a store
        Context mContext = this;
        RecipeStore store = new RecipeStore(mContext, "recipes");

        // Retrieve recipe id
        String id = getIntent().getStringExtra(KEY_ID);

        // Retrieve recipe
        Recipe recipe = store.getRecipe(id);

        // Handle null recipes
        if(recipe == null) {
            titleView.setVisibility(View.GONE);
            descriptionView.setText(R.string.recipe_not_found);
            return;
        }

        // Handle non-null recipes
        titleView.setText(recipe.title);
        descriptionView.setText(recipe.description);
    }
}
