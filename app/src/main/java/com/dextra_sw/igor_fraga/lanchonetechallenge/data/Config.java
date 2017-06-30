package com.dextra_sw.igor_fraga.lanchonetechallenge.data;

/**
 * Created by intercont on 26/06/17.
 */

public class Config {
    //URLs for the API
    public static final String SANDWICHES_URL = "http://192.168.0.100:8080/api/lanche";
    public static final String INGREDIENTS_URL = "http://192.168.0.100:8080/api/ingrediente";
    public static final String INGREDIENTS_FROM_SANDWICH_URL = "http://192.168.0.100:8080/api/ingrediente";
    public static final String ORDERS_URL = "http://192.168.0.100:8080/api/pedido/";
    public static final String PROMO_URL = "http://192.168.0.100:8080/api/promocao/";

    //Tags for the JSON
    public static final String ID = "id";
    public static final String ID_SANDWICH = "id_sandwich";
    public static final String FROM = "/de/";
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String DESCRIPTION = "description";
    public static final String SANDWICH_INGREDIENTS = "ingredients";
    public static final String INGREDIENT_PRICE = "price";
    public static final String EXTRAS = "extras";



}
