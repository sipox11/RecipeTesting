package com.sipox11.recipe_testing.injection;

import android.app.Application;

import com.sipox11.recipe_testing.data.local.Favorites;
import com.sipox11.recipe_testing.data.local.SharedPreferencesFavorites;

public class RecipeApplication extends Application {

    private Favorites favorites = null;

    public Favorites getFavorites() {
        if(favorites == null) {
            favorites = new SharedPreferencesFavorites(this);
        }
        return favorites;
    }
}
