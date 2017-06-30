package com.dextra_sw.igor_fraga.lanchonetechallenge.promo;

import org.json.JSONArray;

/**
 * Created by intercont on 30/06/17.
 */

public interface PromoPresenter {
    void loadPromoData();
    void parsePromoData(JSONArray response);
}
