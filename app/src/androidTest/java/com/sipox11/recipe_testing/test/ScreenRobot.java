package com.sipox11.recipe_testing.test;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public abstract class ScreenRobot<T extends ScreenRobot> {

    public T checkIsHidden(@IdRes int... viewIds) {
        for(int viewId: viewIds) {
            onView(withId(viewId))
                    .check(matches(not(isDisplayed())));
        }
        return (T) this;
    }
}
