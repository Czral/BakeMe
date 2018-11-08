package com.example.xxx.bakeme;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

public class BakeLoader extends android.content.AsyncTaskLoader<List<Recipe>> {

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public BakeLoader(Context context) {

        super(context);
    }

    @Nullable
    @Override
    public List<Recipe> loadInBackground() {

        List<Recipe> list = BakeUtils.fetchBakeData(url);
        return list;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
