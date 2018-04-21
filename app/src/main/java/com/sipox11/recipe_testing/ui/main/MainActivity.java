package com.sipox11.recipe_testing.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sipox11.recipe_testing.R;
import com.sipox11.recipe_testing.data.local.RecipeStore;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        
        RecyclerView recyclerView = findViewById(R.id.recipes);
        RecipeStore store = new RecipeStore(mContext, "recipes");
        RecipeAdapter adapter = new RecipeAdapter(store);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }
}