package com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients;

import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;

import java.util.List;

/**
 * Created by intercont on 29/06/17.
 */

public interface EditIngredientsPresenter {
//    void loadIngredientsList(int id);
//    void parseIngredientsList(JSONArray array);
    List<Ingredient> addIngredientToTheList(Ingredient ingredient, List<Ingredient> listIngredients);
}
