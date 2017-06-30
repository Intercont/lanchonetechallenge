//package com.dextra_sw.igor_fraga.lanchonetechallenge.data;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
///**
// * Created by Igor Fraga on 25/06/17. Sera usado para futura implementacao de DB e Content Providers
// */
//
//public class LanchoneteDbHelper extends SQLiteOpenHelper {
//
//    public static final String TAG = LanchoneteDbHelper.class.getSimpleName();
//
//    public static final String DATABASE_NAME = "lanchonete.db";
//
//    private static final int DATABASE_VERSION = 1;
//
//    public LanchoneteDbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        onUpgrade(db,1, 1);
//        final String SQL_CREATE_INGREDIENTS_TABLE =
//                "CREATE TABLE " + LanchoneteContract.IngredientsEntry.TABLE_NAME + " (" +
//                        LanchoneteContract.IngredientsEntry.COLUMN_INGREDIENT_ID + " INTEGER PRIMARY KEY, " +
//                        LanchoneteContract.IngredientsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
//                        LanchoneteContract.IngredientsEntry.COLUMN_PRICE + " REAL NOT NULL, " +
//                        LanchoneteContract.IngredientsEntry.COLUMN_IMAGE_URL + " TEXT NOT NULL, " +
//                        " UNIQUE (" + LanchoneteContract.IngredientsEntry.COLUMN_INGREDIENT_ID + ") ON CONFLICT REPLACE);";
//
//        Log.d(TAG, SQL_CREATE_INGREDIENTS_TABLE);
//        db.execSQL(SQL_CREATE_INGREDIENTS_TABLE);
//
//        final String SQL_CREATE_SANDWICH_TABLE =
//                "CREATE TABLE " + LanchoneteContract.SandwichEntry.TABLE_NAME + " (" +
//                        LanchoneteContract.SandwichEntry.COLUMN_SANDWICH_ID + " INTEGER PRIMARY KEY, " +
//                        LanchoneteContract.SandwichEntry.COLUMN_NAME + " TEXT NOT NULL, " +
//                        LanchoneteContract.SandwichEntry.COLUMN_PRICE + " REAL NOT NULL, " +
//                        LanchoneteContract.SandwichEntry.COLUMN_IMAGE_URL + " TEXT NOT NULL, " +
//                        " UNIQUE (" + LanchoneteContract.SandwichEntry.COLUMN_SANDWICH_ID + ") ON CONFLICT REPLACE);";
//
//        Log.d(TAG, SQL_CREATE_SANDWICH_TABLE);
//        db.execSQL(SQL_CREATE_SANDWICH_TABLE);
//
//        final String SQL_CREATE_SANDWICH_INGREDIENTS_TABLE =
//                "CREATE TABLE " + LanchoneteContract.SandwichIngredientsEntry.TABLE_NAME + " (" +
//                        LanchoneteContract.SandwichIngredientsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        LanchoneteContract.SandwichIngredientsEntry.COLUMN_INGREDIENT_ID + " INTEGER REFERENCES " +
//                            LanchoneteContract.IngredientsEntry.TABLE_NAME + " (" + LanchoneteContract.IngredientsEntry.COLUMN_INGREDIENT_ID +
//                            "), " +
//                        LanchoneteContract.SandwichIngredientsEntry.COLUMN_SANDWICH_ID + " INTEGER REFERENCES " +
//                            LanchoneteContract.SandwichEntry.TABLE_NAME + " (" + LanchoneteContract.SandwichEntry.COLUMN_SANDWICH_ID +
//                            "));";
//
//        Log.d(TAG, SQL_CREATE_SANDWICH_INGREDIENTS_TABLE);
//        db.execSQL(SQL_CREATE_SANDWICH_INGREDIENTS_TABLE);
//
//        final String SQL_CREATE_PROMO_TABLE =
//                "CREATE TABLE " + LanchoneteContract.PromoEntry.TABLE_NAME + " (" +
//                        LanchoneteContract.PromoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        LanchoneteContract.PromoEntry.COLUMN_NAME + " TEXT NOT NULL, " +
//                        LanchoneteContract.PromoEntry.COLUMN_DESC + " TEXT NOT NULL" + ");";
//
//        Log.d(TAG, SQL_CREATE_PROMO_TABLE);
//        db.execSQL(SQL_CREATE_PROMO_TABLE);
//
//        final String SQL_CREATE_ORDERS_TABLE =
//                "CREATE TABLE " + LanchoneteContract.OrdersEntry.TABLE_NAME + " (" +
//                        LanchoneteContract.OrdersEntry.COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        LanchoneteContract.OrdersEntry.COLUMN_ORDER_SANDWICH_ID + " INTEGER NOT NULL, " +
//                        LanchoneteContract.OrdersEntry.COLUMN_ORDER_SANDWICH_EXTRAS + " INTEGER NOT NULL, " +
//                        LanchoneteContract.OrdersEntry.COLUMN_ORDER_DATE + " INTEGER NOT NULL" +
//                        ");";
//
//        Log.d(TAG, SQL_CREATE_ORDERS_TABLE);
//        db.execSQL(SQL_CREATE_ORDERS_TABLE);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + LanchoneteContract.SandwichIngredientsEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + LanchoneteContract.SandwichEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + LanchoneteContract.IngredientsEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + LanchoneteContract.PromoEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + LanchoneteContract.OrdersEntry.TABLE_NAME);
//        onCreate(db);
//    }
//
//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
////        onUpgrade(db,1, 1);
//        if(!db.isReadOnly()){
//            db.execSQL("PRAGMA foreign_keys=ON;"); //foreign keys constraint
//        }
//    }
//}
