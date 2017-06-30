package com.dextra_sw.igor_fraga.lanchonetechallenge.selectingredientslist;

import org.json.JSONArray;

/**
 * Created by intercont on 29/06/17.
 */

public interface SelectIngredientsPresenter {
    void loadIngredientsList();
    void parseIngredientsList(JSONArray array);


}
