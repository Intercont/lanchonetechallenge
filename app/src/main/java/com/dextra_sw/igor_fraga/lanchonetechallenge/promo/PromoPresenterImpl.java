package com.dextra_sw.igor_fraga.lanchonetechallenge.promo;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Promotional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intercont on 30/06/17.
 */

public class PromoPresenterImpl implements PromoPresenter {

    private PromoActivity promoActivity;
    private List<Promotional> listPromos;

    public PromoPresenterImpl(PromoActivity promoActivity) {
        this.promoActivity = promoActivity;
    }

    @Override
    public void loadPromoData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.PROMO_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parsePromoData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(promoActivity);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void parsePromoData(JSONArray response) {
        listPromos = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Promotional promo = new Promotional();
            JSONObject json = null;

            try {
                json = response.getJSONObject(i);

                promo.setId(json.getInt(Config.ID));
                promo.setName(json.getString(Config.NAME));
                promo.setDescription(json.getString(Config.DESCRIPTION));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listPromos.add(promo);


        }

        promoActivity.mPromoAdapter = new PromoAdapter(promoActivity, listPromos);
        //Adding adapter to recyclerview
        promoActivity.mRecyclerView.setAdapter(promoActivity.mPromoAdapter);
    }
}
