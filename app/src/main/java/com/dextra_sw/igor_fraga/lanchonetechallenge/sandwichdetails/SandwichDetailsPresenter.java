package com.dextra_sw.igor_fraga.lanchonetechallenge.sandwichdetails;

import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

/**
 * Created by intercont on 26/06/17.
 */

public interface SandwichDetailsPresenter {
    Sandwich calculateNewSandwichValues(Sandwich receivedSandwich);
    void addToCart(String url, Sandwich sandwichToCart);

}
