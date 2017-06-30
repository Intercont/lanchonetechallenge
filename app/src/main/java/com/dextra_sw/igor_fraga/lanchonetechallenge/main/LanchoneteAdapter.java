package com.dextra_sw.igor_fraga.lanchonetechallenge.main;

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
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Igor Fraga on 25/06/17.
 */

public class LanchoneteAdapter extends RecyclerView.Adapter<LanchoneteAdapter.LanchoneteAdapterViewHolder> {

    private final LanchoneteAdapterOnClickHandler mClickHandler;

    private final Context mContext;

    private ImageLoader mImageLoader;

    List<Sandwich> sandwiches;


    public LanchoneteAdapter(Context mContext, LanchoneteAdapterOnClickHandler clickHandler) {
        this.mContext = mContext;
        this.mClickHandler = clickHandler;
    }

    public interface LanchoneteAdapterOnClickHandler {
        void onClick(Sandwich sandwich);
    }

    @Override
    public LanchoneteAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_lanchonete, viewGroup, false);

        view.setFocusable(true);
        return new LanchoneteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanchoneteAdapterViewHolder holder, int position) {

        Sandwich sandwich = sandwiches.get(position);
        sandwich.setIngredientsListed(getListOfIngredients(sandwich));

        mImageLoader = LanchoneteSingleton.getInstance(mContext).getImageLoader();
        mImageLoader.get(sandwich.getImageUrl(), ImageLoader.getImageListener(holder.picture,
                R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.picture.setImageUrl(sandwich.getImageUrl(), mImageLoader);
        holder.mealName.setText(sandwich.getName());
        holder.mealPrice.setText(mContext.getString(R.string.format_price, sandwich.getPrice()));
        holder.ingredients.setText(sandwich.getIngredientsListed());
    }

    @NonNull
    public static String getListOfIngredients(Sandwich sandwich) {
        Set<Integer> singleIngredientId = new HashSet<>();
        StringBuilder ingredients = new StringBuilder();

        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            if(singleIngredientId.add(sandwich.getIngredients().get(i).getId())){
                ingredients.append(sandwich.getIngredients().get(i).getName());
                if(i != (sandwich.getIngredients().size() - 1)){
                    ingredients.append(", ");
                }
            }
        }
        return ingredients.toString();
    }

    @Override
    public int getItemCount() {
        return sandwiches.size();
    }

    public List<Sandwich> getSandwichList() {
        return sandwiches;
    }

    public void swapSandwichList(List<Sandwich> sandwiches){
        if(sandwiches != null){
            this.sandwiches = sandwiches;
            notifyDataSetChanged();
        }
    }

    public class LanchoneteAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final NetworkImageView picture;
        final TextView mealName;
        final TextView ingredients;
        final TextView mealPrice;

        public LanchoneteAdapterViewHolder(View itemView) {
            super(itemView);

            picture = (NetworkImageView) itemView.findViewById(R.id.list_item_photo);
            mealName = (TextView) itemView.findViewById(R.id.list_item_meal_name);
            ingredients = (TextView) itemView.findViewById(R.id.list_item_ingredients);
            mealPrice = (TextView) itemView.findViewById(R.id.list_item_meal_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(sandwiches.get(getAdapterPosition()));
        }
    }
}
