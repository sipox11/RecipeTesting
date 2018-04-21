package com.sipox11.recipe_testing.ui.recipe;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.InMemoryFavorites;
import com.sipox11.recipe_testing.injection.TestRecipeApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public class RecipeActivityTest {

    private static final String CARROTS_ID = "creamed_carrots";
    @Rule
    public ActivityTestRule<RecipeActivity> activityRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    private InMemoryFavorites favorites;

    @Before
    public void clearFavorites() {
        TestRecipeApplication app = (TestRecipeApplication)
                InstrumentationRegistry.getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) app.getFavorites();
        favorites.clear();
    }

    @Test
    public void recipeNotFound() {
        activityRule.launchActivity(null);
        // Verify that it will show recipe not found
        onView(withId(R.id.description)).check(matches(withText(R.string.recipe_not_found)));
        // Verify that the title is not displayed
        onView(withId(R.id.title)).check(matches(not(isDisplayed())));
    }

    @Test
    public void clickToFavorite() {
        launchRecipe(CARROTS_ID);
        // Verify that the title textview displays the correct text,
        // that it is not selected initially
        // and that when clicked, it changes to selected
        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }

    @Test
    public void alreadyFavorite() {
        favorites.put(CARROTS_ID, true);
        launchRecipe(CARROTS_ID);

        // Verify that carrots recipe is selected on startup by default
        onView(withId(R.id.title))
                .check(matches(isSelected()));

    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityRule.launchActivity(intent);
    }
}