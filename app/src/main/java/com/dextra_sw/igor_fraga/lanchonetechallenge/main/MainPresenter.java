package com.dextra_sw.igor_fraga.lanchonetechallenge.main;

import org.json.JSONArray;

/**
 * Created by intercont on 26/06/17.
 */

public interface MainPresenter {
    void loadIngredientsData();
    void loadSandwichData();
    void parseSandwichData(JSONArray array);
    void parseIngredientsData(JSONArray array);
}
