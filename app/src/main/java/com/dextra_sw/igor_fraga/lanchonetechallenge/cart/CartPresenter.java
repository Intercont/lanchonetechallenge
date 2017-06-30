package com.dextra_sw.igor_fraga.lanchonetechallenge.cart;

import org.json.JSONArray;

/**
 * Created by intercont on 30/06/17.
 */

public interface CartPresenter {
    void loadCartData();
    void parseCartData(JSONArray response);
}
