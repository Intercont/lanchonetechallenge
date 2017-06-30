package com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients;

import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;

import java.util.List;

/**
 * Created by intercont on 29/06/17.
 */

public class EditIngredientsPresenterImpl implements EditIngredientsPresenter {

    private EditIngredientsActivity editIngredientsActivity;
    private List<Ingredient> listIngredients;

    public EditIngredientsPresenterImpl(EditIngredientsActivity editIngredientsActivity){
        this.editIngredientsActivity = editIngredientsActivity;
    }

    @Override
    public List<Ingredient> addIngredientToTheList(Ingredient addedIngredient, List<Ingredient> listIngredients) {
        boolean hasBeenAdded = false;
        //validate if the ingredient is already in the list
        for (int i = 0; i < listIngredients.size(); i++) {
            Ingredient ingredient = listIngredients.get(i);
            if(ingredient.getId() == addedIngredient.getId()){
                listIngredients.get(i).setQuantity(listIngredients.get(i).getQuantity() + 1);
                hasBeenAdded = true;
            }
        }
        //if not add it
        if(!hasBeenAdded) {
            listIngredients.add(addedIngredient);
        }
        return listIngredients;
    }
}
