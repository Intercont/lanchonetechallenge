package com.dextra_sw.igor_fraga.lanchonetechallenge.selectingredientslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.LanchoneteSingleton;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import java.util.List;

/**
 * Created by intercont on 29/06/17.
 */

public class SelectIngredientsAdapter extends RecyclerView.Adapter<SelectIngredientsAdapter.SelectIngredientsAdapterViewHolder> {

    private final SelectIngredientsAdapterOnClickHandler mClickHandler;

    private final Context mContext;

    private ImageLoader mImageLoader;

    List<Ingredient> ingredients;


    public SelectIngredientsAdapter(Context mContext, SelectIngredientsAdapterOnClickHandler clickHandler) {
        this.mContext = mContext;
        this.mClickHandler = clickHandler;
    }

    public interface SelectIngredientsAdapterOnClickHandler {
        void onClick(Ingredient ingredient);
    }

    @Override
    public SelectIngredientsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_ingredient_selection, viewGroup, false);

        view.setFocusable(true);
        return new SelectIngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectIngredientsAdapterViewHolder holder, int position) {

        Ingredient ingredient = ingredients.get(position);

        mImageLoader = LanchoneteSingleton.getInstance(mContext).getImageLoader();
        mImageLoader.get(ingredient.getImageUrl(), ImageLoader.getImageListener(holder.picture,
                R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.picture.setImageUrl(ingredient.getImageUrl(), mImageLoader);
        holder.ingredientName.setText(ingredient.getName());
    }

    @NonNull
    private String getListOfIngredients(Sandwich sandwich) {
        StringBuilder ingredients = new StringBuilder();

        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            ingredients.append(sandwich.getIngredients().get(i).getName());
            if(i != (sandwich.getIngredients().size() - 1)){
                ingredients.append(", ");
            }
        }
        return ingredients.toString();
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void swapIngredientsList(List<Ingredient> ingredients){
        if(ingredients != null){
            this.ingredients = ingredients;
            notifyDataSetChanged();
        }
    }

    public class SelectIngredientsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final NetworkImageView picture;
        final TextView ingredientName;

        public SelectIngredientsAdapterViewHolder(View itemView) {
            super(itemView);

            picture = (NetworkImageView) itemView.findViewById(R.id.select_ingredients_photo);
            ingredientName = (TextView) itemView.findViewById(R.id.select_ingredients_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //TODO set the ingredient to the list of ingredients of the sandwich
            mClickHandler.onClick(ingredients.get(getAdapterPosition()));
        }
    }


}
