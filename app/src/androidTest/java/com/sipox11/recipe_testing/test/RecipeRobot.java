package com.sipox11.recipe_testing.test;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.InMemoryFavorites;
import com.sipox11.recipe_testing.injection.TestRecipeApplication;
import com.sipox11.recipe_testing.ui.recipe.RecipeActivity;

public class RecipeRobot extends ScreenRobot<RecipeRobot> {

    private final InMemoryFavorites favorites;

    public RecipeRobot() {
        TestRecipeApplication app = (TestRecipeApplication)
                InstrumentationRegistry.getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) app.getFavorites();
        favorites.clear();
    }

    public RecipeRobot launch(ActivityTestRule rule) {
        rule.launchActivity(null);
        return this;
    }

    public RecipeRobot launch(ActivityTestRule rule, String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        rule.launchActivity(intent);
        return this;
    }

    public RecipeRobot noTitle() {
        return checkIsHidden(R.id.title);
    }

    public RecipeRobot title(@StringRes int stringId) {
        return checkViewHasText(R.id.title, stringId);
    }

    public RecipeRobot isFavorite() {
        return checkIsSelected(R.id.title);
    }

    public RecipeRobot isNotFavorite() {
        return checkIsNotSelected(R.id.title);
    }

    public RecipeRobot description(@StringRes int stringId) {
        return checkViewHasText(R.id.description, stringId);
    }

    public RecipeRobot setFavorite(String id) {
        favorites.put(id, true);
        return this;
    }

    public RecipeRobot clickTitle() {
        return clickView(R.id.title);
    }


}
