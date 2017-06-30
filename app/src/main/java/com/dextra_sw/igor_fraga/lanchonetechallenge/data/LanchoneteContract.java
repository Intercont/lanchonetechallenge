package com.dextra_sw.igor_fraga.lanchonetechallenge.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Igor Fraga on 25/06/17.
 */

public class LanchoneteContract {

    public static final String CONTENT_AUTHORITY = "com.dextra_sw.igor_fraga.lanchonetechallenge";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_SANDWICHES = "sandwiches";
    public static final String PATH_INGREDIENTS = "ingredients";
    public static final String PATH_SANDWICH_INGREDIENTS = "sandwich_ingredients";
    public static final String PATH_PROMO = "promo";
    public static final String PATH_ORDERS = "orders";

    public static final class SandwichEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SANDWICHES)
                .build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SANDWICHES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SANDWICHES;

        public static final String TABLE_NAME = "sandwiches";

        public static final String COLUMN_SANDWICH_ID = "sandwich_id"; //is not the same as the row ID
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_PRICE = "price";

    }

    public static final class IngredientsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INGREDIENTS)
                .build();

        public static final String TABLE_NAME = "ingredients";

        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE_URL = "image_url";
    }

    public static final class SandwichIngredientsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SANDWICH_INGREDIENTS)
                .build();

        public static final String TABLE_NAME = "sandwich_ingredients";

        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";
        public static final String COLUMN_SANDWICH_ID = "sandwich_id";
    }

    public static final class PromoEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_PROMO)
                .build();

        public static final String TABLE_NAME = "promo";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
    }

    public static final class OrdersEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_ORDERS)
                .build();

        public static final String TABLE_NAME = "orders";

        public static final String COLUMN_ORDER_ID = "id";
        public static final String COLUMN_ORDER_SANDWICH_ID = "sandwich_id";
        public static final String COLUMN_ORDER_SANDWICH_EXTRAS = "sandwich_extras";
        public static final String COLUMN_ORDER_DATE = "date";

        public static Uri buildOrderUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
