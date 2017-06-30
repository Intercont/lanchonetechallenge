package com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients;

import android.content.Context;
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

import java.util.List;

/**
 * Created by intercont on 28/06/17.
 */

public class EditIngredientsAdapter extends RecyclerView.Adapter<EditIngredientsAdapter.EditIngredientsAdapterViewHolder> {

    private final Context mContext;

    private ImageLoader mImageLoader;

    List<Ingredient> ingredients;


    public EditIngredientsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public EditIngredientsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_ingredient_quantity, viewGroup, false);

        view.setFocusable(true);
        return new EditIngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EditIngredientsAdapterViewHolder holder, int position) {

        Ingredient ingredient = ingredients.get(position);

        mImageLoader = LanchoneteSingleton.getInstance(mContext).getImageLoader();
        mImageLoader.get(ingredient.getImageUrl(), ImageLoader.getImageListener(holder.picture,
                R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.picture.setImageUrl(ingredient.getImageUrl(), mImageLoader);
        holder.ingredientQuantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.ingredientName.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void swapEditIngredientsList(List<Ingredient> ingredients){
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public List<Ingredient> getIngredientsListFromAdapter(){
        return ingredients;
    }

    public class EditIngredientsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final NetworkImageView picture;
        final TextView ingredientQuantity;
        final TextView ingredientName;

        EditIngredientsActivity editIngredientsActivity;

        public EditIngredientsAdapterViewHolder(View itemView) {
            super(itemView);

            picture = (NetworkImageView) itemView.findViewById(R.id.edit_ingredients_photo);
            ingredientName = (TextView) itemView.findViewById(R.id.edit_ingredients_name);
            ingredientQuantity = (TextView) itemView.findViewById(R.id.edit_ingredients_quantity);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //TODO Set id of the selected meal
//            Toast.makeText(mContext, mealName.getText().toString(), Toast.LENGTH_SHORT).show();
//            String bitmap = sandwiches.get(getAdapterPosition()).getImageUrl();
//
//            mClickHandler.onClick(getAdapterPosition() + 1, mealName.getText().toString(),
//                    mealPrice.getText().toString(), ingredients.getText().toString(), bitmap);
        }
    }
}
