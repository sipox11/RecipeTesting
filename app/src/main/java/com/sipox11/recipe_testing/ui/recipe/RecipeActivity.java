package com.sipox11.recipe_testing.ui.recipe;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.Favorites;
import com.sipox11.recipe_testing.data.local.RecipeStore;
import com.sipox11.recipe_testing.data.local.SharedPreferencesFavorites;
import com.sipox11.recipe_testing.data.model.Recipe;
import com.sipox11.recipe_testing.injection.RecipeApplication;

public class RecipeActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Get ui elements
        final TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);

        // Create a store
        Context mContext = this;
        RecipeStore store = new RecipeStore(mContext, "recipes");

        // Retrieve recipe id
        String id = getIntent().getStringExtra(KEY_ID);

        // Retrieve recipe
        final Recipe recipe = store.getRecipe(id);

        // Handle null recipes
        if(recipe == null) {
            titleView.setVisibility(View.GONE);
            descriptionView.setText(R.string.recipe_not_found);
            return;
        }

        // Inject Shared Preferences Favorites dependency from application
        RecipeApplication app = (RecipeApplication) getApplication();
        final Favorites favorites = app.getFavorites();
        boolean favorite = favorites.get(recipe.id);

        // Handle non-null recipes
        titleView.setText(recipe.title);
        titleView.setSelected(favorite);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = favorites.toggle(recipe.id);
                titleView.setSelected(result);
            }
        });
        descriptionView.setText(recipe.description);
    }
}
