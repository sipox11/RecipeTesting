package com.sipox11.recipe_testing.injection;

import com.sipox11.recipe_testing.data.local.Favorites;
import com.sipox11.recipe_testing.data.local.InMemoryFavorites;

public class TestRecipeApplication extends RecipeApplication {

    private final Favorites favorites = new InMemoryFavorites();

    @Override
    public Favorites getFavorites() {
        return favorites;
    }
}
