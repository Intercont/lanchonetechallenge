package com.dextra_sw.igor_fraga.lanchonetechallenge.cart;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intercont on 30/06/17.
 */

public class CartPresenterImpl implements CartPresenter {

    private CartActivity cartActivity;
    private List<Integer> listIdSandwiches;
    private List<Sandwich> selectedSandwiches;
    private List<Sandwich> allSandwiches;

    public CartPresenterImpl(CartActivity cartActivity, List<Sandwich> allSandwiches) {
        this.cartActivity = cartActivity;
        this.allSandwiches = allSandwiches;
    }

    @Override
    public void loadCartData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.ORDERS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseCartData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(cartActivity);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    public void swapSandwichList(List<Sandwich> allSandwiches){
        if(allSandwiches != null){
            this.allSandwiches = allSandwiches;
        }
    }

    @Override
    public void parseCartData(JSONArray response) {
        listIdSandwiches = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            int id = 0;
            JSONObject json = null;

            try {
                json = response.getJSONObject(i);

                id = json.getInt(Config.ID_SANDWICH);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listIdSandwiches.add(id);
        }

        cartActivity.mCartAdapter = new CartAdapter(cartActivity, getSandwichesById(listIdSandwiches));
        //Adding adapter to recyclerview
        cartActivity.mRecyclerView.setAdapter(cartActivity.mCartAdapter);
    }

    private List<Sandwich> getSandwichesById(List<Integer> listIdSandwiches) {
        selectedSandwiches = new ArrayList<>();

        for(Sandwich sandwich : allSandwiches){
            for (Integer id : listIdSandwiches){
                if (sandwich.getId() == id)
                    selectedSandwiches.add(sandwich);
            }
        }
        return selectedSandwiches;
    }
}
