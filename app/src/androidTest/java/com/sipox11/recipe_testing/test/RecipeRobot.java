package com.sipox11.recipe_testing.test;

import com.sipox11.recipe_testing.R;

public class RecipeRobot extends ScreenRobot<RecipeRobot> {

    public RecipeRobot noTitle() {
        return checkIsHidden(R.id.title);
    }
}
