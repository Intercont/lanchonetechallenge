package com.dextra_sw.igor_fraga.lanchonetechallenge.main;

import android.app.ProgressDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by intercont on 26/06/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private List<Sandwich> listSandwiches;
    private ArrayList<Ingredient> listIngredients;
    private MainActivity mainActivity;
    ProgressDialog loading;



    public MainPresenterImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        listSandwiches = new ArrayList<>();
        listIngredients = new ArrayList<>();
        loading = null;
    }

    @Override
    public void loadIngredientsData() {
        loading = ProgressDialog.show(mainActivity, "Loading Data", "Please wait...", false, false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.INGREDIENTS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        parseIngredientsData(response);
                        loadSandwichData();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void loadSandwichData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.SANDWICHES_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseSandwichData(response);
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }



    @Override
    public void parseIngredientsData(JSONArray array) {
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
    }

    @Override
    public void parseSandwichData(JSONArray array) {
        for(int i = 0; i<array.length(); i++) {
            Sandwich sandwich = new Sandwich();
            double price = 0.0;

            ArrayList<Ingredient> ingredientsForThisSandwich;

            JSONArray ingredients = new JSONArray();
            JSONObject json = null;

            try {
                json = array.getJSONObject(i);

                sandwich.setId(json.getInt(Config.ID));
                sandwich.setName(json.getString(Config.NAME));
                sandwich.setImageUrl(json.getString(Config.IMAGE));

                //receive ingredients id for this sandwich
                ingredients = json.getJSONArray(Config.SANDWICH_INGREDIENTS);
                ingredientsForThisSandwich = new ArrayList<>();
                Set<Integer> singleIngredientId = new HashSet<>();

                //verify each ingredient
                for (int j = 0; j < ingredients.length(); j++) {

                    int ingredientId = Integer.parseInt(ingredients.getString(j));

                    for (Ingredient ingredient : listIngredients) {
                        if ((ingredient.getId() == ingredientId) && singleIngredientId.add(ingredient.getId())){
                            ingredientsForThisSandwich.add(ingredient);
                            price += ingredient.getPrice();
                            break;
                        }
                    }
                }
                sandwich.setIngredients(ingredientsForThisSandwich);
                sandwich.setPrice(price);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSandwiches.add(sandwich);
        }


        //Finally initializing our adapter
        mainActivity.mLanchoneteAdapter = new LanchoneteAdapter(mainActivity, mainActivity);
        mainActivity.mLanchoneteAdapter.swapSandwichList(listSandwiches);

        //Adding adapter to recyclerview
        mainActivity.mRecyclerView.setAdapter(mainActivity.mLanchoneteAdapter);
    }
}
