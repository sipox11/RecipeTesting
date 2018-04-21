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
        // STEP 1: Set up the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);

        // STEP 2: Load recipe from store
        Context mContext = this;
        RecipeStore store = new RecipeStore(mContext, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        final Recipe recipe = store.getRecipe(id);

        // STEP 3: If recipe is null show error
        if(recipe == null) {
            titleView.setVisibility(View.GONE);
            descriptionView.setText(R.string.recipe_not_found);
            return;
        }

        // STEP 4: If recipe is not null we will show the recipe
        // Inject Shared Preferences Favorites dependency from application
        RecipeApplication app = (RecipeApplication) getApplication();
        final Favorites favorites = app.getFavorites();
        boolean favorite = favorites.get(recipe.id);

        titleView.setText(recipe.title);
        titleView.setSelected(favorite);
        descriptionView.setText(recipe.description);

        // STEP 5: When title is clicked, toggle favorites
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = favorites.toggle(recipe.id);
                titleView.setSelected(result);
            }
        });

    }
}
