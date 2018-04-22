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
import com.sipox11.recipe_testing.injection.RecipeApplication;

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {

    public static final String KEY_ID = "id";
    private TextView titleView;
    private TextView descriptionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // STEP 1: Set up the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        titleView = findViewById(R.id.title);
        descriptionView = findViewById(R.id.description);

        
        Context mContext = this;
        RecipeStore store = new RecipeStore(mContext, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);

        RecipeApplication app = (RecipeApplication) getApplication();
        final Favorites favorites = app.getFavorites();

        final RecipePresenter presenter = new RecipePresenter(store, this, favorites);
        presenter.loadRecipe(id);

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleFavorite();
            }
        });

    }

    @Override
    public void showRecipeNotFoundError() {
        titleView.setVisibility(View.GONE);
        descriptionView.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void setFavorite(boolean favorite) {
        titleView.setSelected(favorite);
    }
}
