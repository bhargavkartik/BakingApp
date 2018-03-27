package com.kartikbhargav.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartikbhargav.bakingapp.Constants.RecipeImageList;
import com.kartikbhargav.bakingapp.Data.Recipe;
import com.kartikbhargav.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kbhargav on 3/25/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context mContext;
    private List<Recipe> recipeList = new ArrayList<>();
    private OnClickInterface mOnClickInterface;

    public RecipeAdapter(Context context, OnClickInterface onClickInterface)
    {
        mContext = context;
        mOnClickInterface = onClickInterface;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_layout, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position)
    {
        Recipe recipe = recipeList.get(position);
        holder.RecipeTitle.setText(recipe.getName());
        if (recipe.getImage() != null && !recipe.getImage().isEmpty())
        {
            Picasso.with(mContext)
                    .load(recipe.getImage())
                    .placeholder(RecipeImageList.recipeImages[position])
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(holder.RecipePoster);
        } else {
            Picasso.with(mContext)
                    .load(RecipeImageList.recipeImages[position])
                    .placeholder(R.drawable.progress_animation)
                    .into(holder.RecipePoster);
        }
    }

    @Override
    public int getItemCount() {
        if (recipeList.size() == 0) {
            return 0;
        }
        return recipeList.size();
    }

    public void setReceipeData(List<Recipe> recipes) {
        recipeList = recipes;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_title)
        TextView RecipeTitle;

        @BindView(R.id.recipe_image)
        ImageView RecipePoster;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Recipe recipe = recipeList.get(position);
            mOnClickInterface.onRecipeClicked(recipe);
        }
    }

    public interface OnClickInterface {
        void onRecipeClicked(Recipe recipe);
    }
}
