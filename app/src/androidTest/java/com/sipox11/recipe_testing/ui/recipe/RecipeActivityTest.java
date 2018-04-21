package com.sipox11.recipe_testing.ui.recipe;

import android.support.test.rule.ActivityTestRule;

import com.sipox11.recipe_testing.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> activityRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound() {
        activityRule.launchActivity(null);
        // Verify that it will show recipe not found
        onView(withId(R.id.description)).check(matches(withText(R.string.recipe_not_found)));
        // Verify that the title is not displayed
        onView(withId(R.id.title)).check(matches(not(isDisplayed())));
    }

    

}