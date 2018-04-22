package com.sipox11.recipe_testing.ui.recipe;

import com.sipox11.recipe_testing.data.local.Favorites;
import com.sipox11.recipe_testing.data.local.RecipeStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class RecipePresenterTest {

    private RecipeStore store;
    private Favorites favorites;
    private RecipeContract.View view;
    private RecipePresenter presenter;

    @Before
    public void setup() {
        store = Mockito.mock(RecipeStore.class);
        favorites = Mockito.mock(Favorites.class);
        view = Mockito.mock(RecipeContract.View.class);
        presenter = new RecipePresenter(store, view, favorites );
    }

    @Test
    public void recipeNotFound() {
        // 1) Arrange: Set up the stage
        // When we call store.getRecipe(String) with any string as param, then store will return null
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        // 2) Act
        presenter.loadRecipe("no_such_recipe");
        // 3) Assert
        // Make sure that the function showRecipeNotFoundError on the object view was called only once
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }



}