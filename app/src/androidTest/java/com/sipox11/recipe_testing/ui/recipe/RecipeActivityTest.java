package com.sipox11.recipe_testing.ui.recipe;

import android.support.test.rule.ActivityTestRule;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.test.RecipeRobot;

import org.junit.Rule;
import org.junit.Test;

public class RecipeActivityTest {

    private static final String CARROTS_ID = "creamed_carrots";
    @Rule
    public ActivityTestRule<RecipeActivity> activityRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound() {
        new RecipeRobot()
                .launch(activityRule)
                .noTitle()
                .description(R.string.recipe_not_found);
    }

    @Test
    public void clickToFavorite() {
        new RecipeRobot()
                .launch(activityRule, CARROTS_ID)
                .title(R.string.creamed_carrots_recipe_title)
                .isNotFavorite()
                .clickTitle()
                .isFavorite();
    }

    @Test
    public void alreadyFavorite() {
        new RecipeRobot()
                .setFavorite(CARROTS_ID)
                .launch(activityRule, CARROTS_ID)
                .isFavorite();
    }

}