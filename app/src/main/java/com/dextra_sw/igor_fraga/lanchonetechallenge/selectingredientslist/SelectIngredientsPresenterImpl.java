package com.dextra_sw.igor_fraga.lanchonetechallenge.selectingredientslist;

import android.app.ProgressDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intercont on 29/06/17.
 */

public class SelectIngredientsPresenterImpl implements SelectIngredientsPresenter {
    private SelectIngredientsActivity selectIngredientsActivity;
    private List<Ingredient> listIngredients;

    public SelectIngredientsPresenterImpl(SelectIngredientsActivity selectIngredientsActivity){
        this.selectIngredientsActivity = selectIngredientsActivity;
        listIngredients = new ArrayList<>();
    }


    @Override
    public void loadIngredientsList() {
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(selectIngredientsActivity, "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.INGREDIENTS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();
                        parseIngredientsList(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(selectIngredientsActivity);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void parseIngredientsList(JSONArray array) {
        for(int i = 0; i< array.length(); i++) {
            Ingredient ingredient = new Ingredient();
            JSONObject json = null;

            try {
                json = array.getJSONObject(i);

                ingredient.setId(json.getInt(Config.ID));
                ingredient.setName(json.getString(Config.NAME));
                ingredient.setImageUrl(json.getString(Config.IMAGE));
                ingredient.setPrice(json.getDouble(Config.INGREDIENT_PRICE));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listIngredients.add(ingredient);
        }

        //Finally initializing our adapter
        selectIngredientsActivity.mSelectIngredientsAdapter = new SelectIngredientsAdapter(selectIngredientsActivity, selectIngredientsActivity);
        selectIngredientsActivity.mSelectIngredientsAdapter.swapIngredientsList(listIngredients);

        //Adding adapter to recyclerview
        selectIngredientsActivity.mRecyclerView.setAdapter(selectIngredientsActivity.mSelectIngredientsAdapter);
    }
}