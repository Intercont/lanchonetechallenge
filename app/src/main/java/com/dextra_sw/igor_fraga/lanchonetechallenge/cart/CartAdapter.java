package com.dextra_sw.igor_fraga.lanchonetechallenge.cart;

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
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import java.util.List;

import static com.dextra_sw.igor_fraga.lanchonetechallenge.main.LanchoneteAdapter.getListOfIngredients;

/**
 * Created by intercont on 30/06/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {

    private final Context mContext;
    List<Sandwich> listSandwiches;
    private ImageLoader mImageLoader;


    public CartAdapter(Context mContext, List<Sandwich> listSandwich) {
        this.mContext = mContext;
        this.listSandwiches = listSandwich;
    }

    @Override
    public CartAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_cart_checkout, viewGroup, false);

        view.setFocusable(true);
        return new CartAdapter.CartAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapterViewHolder holder, int position) {
        Sandwich sandwich = listSandwiches.get(position);
        sandwich.setIngredientsListed(getListOfIngredients(sandwich));

        mImageLoader = LanchoneteSingleton.getInstance(mContext).getImageLoader();
        mImageLoader.get(sandwich.getImageUrl(), ImageLoader.getImageListener(holder.picture,
                R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.picture.setImageUrl(sandwich.getImageUrl(), mImageLoader);
        holder.mealName.setText(sandwich.getName());
        holder.mealPrice.setText(mContext.getString(R.string.format_price, sandwich.getPrice()));
        holder.ingredients.setText(sandwich.getIngredientsListed());
    }


        @Override
        public int getItemCount () {
            return listSandwiches.size();
        }

        public class CartAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            final NetworkImageView picture;
            final TextView mealName;
            final TextView ingredients;
            final TextView mealPrice;

            public CartAdapterViewHolder(View itemView) {
                super(itemView);

                picture = (NetworkImageView) itemView.findViewById(R.id.cart_item_photo);
                mealName = (TextView) itemView.findViewById(R.id.cart_item_meal_name);
                ingredients = (TextView) itemView.findViewById(R.id.cart_item_ingredients);
                mealPrice = (TextView) itemView.findViewById(R.id.cart_item_meal_price);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
            }
        }
    }
