package com.example.xxx.bakeme;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        android.app.LoaderManager.LoaderCallbacks<List<Recipe>>,
        MainRecyclerViewAdapter.ListItemClickListener {

    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_list_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        android.app.LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public android.content.Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        return new BakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {

        if (data != null && !data.isEmpty()) {

            recipes = data;
            mainRecyclerViewAdapter = new MainRecyclerViewAdapter(data, this);
            recyclerView.setAdapter(mainRecyclerViewAdapter);

        } else {

            //TODO: Display loading bar.

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }

    @Override
    public void onListItemClick(int position) {

        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);

        Recipe recipe = recipes.get(position);

        String name = recipe.getName();

        intent.putExtra("NAME", name);

        startActivity(intent);

    }
}
