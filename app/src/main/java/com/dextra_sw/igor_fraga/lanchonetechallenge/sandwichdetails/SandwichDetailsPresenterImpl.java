package com.dextra_sw.igor_fraga.lanchonetechallenge.sandwichdetails;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.main.LanchoneteAdapter;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by intercont on 26/06/17.
 */

public class SandwichDetailsPresenterImpl implements SandwichDetailsPresenter {

    SandwichDetailsActivity sandwichDetailsActivity;

    public SandwichDetailsPresenterImpl(SandwichDetailsActivity sandwichDetailsActivity){
        this.sandwichDetailsActivity = sandwichDetailsActivity;
    }

    @Override
    public Sandwich calculateNewSandwichValues(Sandwich receivedSandwich) {
        List<Ingredient> receivedListOfIngredients = receivedSandwich.getIngredients();

        //update Ingredient List to display
        receivedSandwich.setIngredientsListed(LanchoneteAdapter.getListOfIngredients(receivedSandwich));

        //update sandwich price
        double sandwichValue = 0.0;
        int qtyDiscountIngredients;

        for(Ingredient receivedIngredient : receivedListOfIngredients) {
            //muita carne ou muito queijo
            if (receivedIngredient.getId() == 3 || receivedIngredient.getId() == 5){
                qtyDiscountIngredients = receivedIngredient.getQuantity() / 3;
                sandwichValue += (receivedIngredient.getPrice() * (receivedIngredient.getQuantity() - qtyDiscountIngredients));
            } else {
                sandwichValue += (receivedIngredient.getPrice() * receivedIngredient.getQuantity());
            }
        }
        //BUSINESS RULE - for sales promotion
        //tem alface e nao tem bacon
        boolean hasAlface = false, hasBacon = false;
        for(Ingredient receivedIngredient : receivedListOfIngredients) {
            if(receivedIngredient.getId() == 1){
                hasAlface = true;
            }
            if(receivedIngredient.getId() == 2){
                hasBacon = true;
            }
        }
        //desconto de 10 por cento se tiver alface e nao tiver bacon
        if(hasAlface && !hasBacon){
            sandwichValue -= ((sandwichValue * 10)/100);
        }

        receivedSandwich.setPrice(sandwichValue);
        return receivedSandwich;
    }

    @Override
    public void addToCart(String url, final Sandwich sandwichToCart){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Toast.makeText(sandwichDetailsActivity, "Sanduíche adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "Erro ao inserir no carrinho da API");
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put(Config.ORDERS_URL, String.valueOf(sandwichToCart.getId()));
//                params.put(Config.EXTRAS, "{\"extras\": \"3\"}\")");

                return params;
            }

        };

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(sandwichDetailsActivity);

        //Adding request to the queue
        requestQueue.add(putRequest);
    }
}
