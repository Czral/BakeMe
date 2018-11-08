package com.example.xxx.bakeme;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {

    private static List<Recipe> recipeList;
    public static ListItemClickListener listener;
    public String name;

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mainTextView;
        public ImageView mainImageView;

        public MainViewHolder(final View view) {

            super(view);
            mainTextView = view.findViewById(R.id.main_Text_View);
            mainImageView = view.findViewById(R.id.main_Image_View);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            listener.onListItemClick(position);
            Recipe recipe = recipeList.get(position);
            name = recipe.getName();
        }

        public void bind(int position) {

            if (recipeList != null) {

                Recipe recipe = recipeList.get(position);
                String name = recipe.getName();
                mainTextView.setText(name);

            } else {

                mainTextView.setText("Please wait");
            }

        }

    }


    public MainRecyclerViewAdapter(List<Recipe> recipes, ListItemClickListener listItemClickListener) {

        listener = listItemClickListener;
        recipeList = recipes;
    }

    public interface ListItemClickListener {

        void onListItemClick(int position);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_recycler_view_item_list, viewGroup, false);

        MainViewHolder mainViewHolder = new MainViewHolder(view);

        if (recipeList != null && !recipeList.isEmpty()) {

            mainViewHolder.mainTextView.setText(recipeList.get(i).getName());
        }
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        mainViewHolder.bind(i);
        mainViewHolder.mainImageView.setImageResource(R.drawable.ic_launcher_background);
    }

    //TODO: FIX THIS
    @Override
    public int getItemCount() {
        return 4;
    }


}
