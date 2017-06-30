//package com.dextra_sw.igor_fraga.lanchonetechallenge.data;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
//import static com.dextra_sw.igor_fraga.lanchonetechallenge.data.LanchoneteContract.PATH_SANDWICH_INGREDIENTS;
//
///**
// * Created by intercont on 27/06/17. Sera usado para futura implementacao de DB e Content Providers
// */
//
//public class LanchoneteProvider extends ContentProvider {
//
//    public static final int CODE_SANDWICHES = 100;
//    public static final int CODE_INGREDIENTS = 200;
//    public static final int CODE_PROMO = 300;
//    public static final int CODE_ORDERS = 400;
//    public static final int CODE_SANDWICH_INGREDIENTS = 500;
//
//    public static final int CODE_SANDWICH_WITH_ID = 101;
//    public static final int CODE_INGREDIENTS_FROM_SANDWICH_ID = 201;
//    public static final int CODE_ORDER_INSERTION = 401;
//
//    private static final UriMatcher sUriMatcher = buildUriMatcher();
////    private LanchoneteDbHelper mOpenHelper;
//
//    public static UriMatcher buildUriMatcher(){
//
//        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//        final String authority = LanchoneteContract.CONTENT_AUTHORITY;
//
//        matcher.addURI(authority, LanchoneteContract.PATH_SANDWICHES, CODE_SANDWICHES);
//        matcher.addURI(authority, LanchoneteContract.PATH_SANDWICHES + "/#", CODE_SANDWICH_WITH_ID);
//
//        matcher.addURI(authority, LanchoneteContract.PATH_INGREDIENTS, CODE_INGREDIENTS);
//        matcher.addURI(authority, LanchoneteContract.PATH_INGREDIENTS + "/#", CODE_INGREDIENTS_FROM_SANDWICH_ID);
//
//        matcher.addURI(authority, PATH_SANDWICH_INGREDIENTS, CODE_SANDWICH_INGREDIENTS);
//
//        matcher.addURI(authority, LanchoneteContract.PATH_PROMO, CODE_PROMO);
//
//        matcher.addURI(authority, LanchoneteContract.PATH_ORDERS, CODE_ORDERS);
//        matcher.addURI(authority, LanchoneteContract.PATH_ORDERS + "/#", CODE_ORDER_INSERTION);
//
//        return matcher;
//    }
//
//
//    @Override
//    public boolean onCreate() {
//        mOpenHelper = new LanchoneteDbHelper(getContext());
//        return true;
//    }
//
//    @Override
//    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
//        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
//        int rowsInserted;
//
//        switch (sUriMatcher.match(uri)) {
//
//            case CODE_INGREDIENTS:
//                db.beginTransaction();
//                rowsInserted = 0;
//                try {
//                    for (ContentValues value : values) {
//
//                        long _id = db.insert(LanchoneteContract.IngredientsEntry.TABLE_NAME, null, value);
//                        if (_id != -1) {
//                            rowsInserted++;
//                        }
//                    }
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//
//                if (rowsInserted > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null);
//                }
//
//                return rowsInserted;
//
//            case CODE_SANDWICHES:
//                db.beginTransaction();
//                rowsInserted = 0;
//                try {
//                    for (ContentValues value : values) {
//
//                        long _id = db.insert(LanchoneteContract.SandwichEntry.TABLE_NAME, null, value);
//                        if (_id != -1) {
//                            rowsInserted++;
//                        }
//                    }
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//
//                if (rowsInserted > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null);
//                }
//
//                return rowsInserted;
//
//            case CODE_PROMO:
//                db.beginTransaction();
//                rowsInserted = 0;
//                try {
//                    for (ContentValues value : values) {
//
//                        long _id = db.insert(LanchoneteContract.PromoEntry.TABLE_NAME, null, value);
//                        if (_id != -1) {
//                            rowsInserted++;
//                        }
//                    }
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//
//                if (rowsInserted > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null);
//                }
//
//                return rowsInserted;
//
//            case CODE_ORDERS:
//                db.beginTransaction();
//                rowsInserted = 0;
//                try {
//                    for (ContentValues value : values) {
//
//                        long _id = db.insert(LanchoneteContract.OrdersEntry.TABLE_NAME, null, value);
//                        if (_id != -1) {
//                            rowsInserted++;
//                        }
//                    }
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//
//                if (rowsInserted > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null);
//                }
//
//                return rowsInserted;
//
//            case CODE_SANDWICH_INGREDIENTS:
//                db.beginTransaction();
//                rowsInserted = 0;
//                try {
//                    for (ContentValues value : values) {
//
//                        long _id = db.insert(LanchoneteContract.SandwichIngredientsEntry.TABLE_NAME, null, value);
//                        if (_id != -1) {
//                            rowsInserted++;
//                        }
//                    }
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//
//                if (rowsInserted > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null);
//                }
//
//                return rowsInserted;
//            default:
//                return super.bulkInsert(uri, values);
//        }
//    }
//
//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//
//        Cursor cursor;
//
//        switch (sUriMatcher.match(uri)) {
//            case CODE_INGREDIENTS: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.IngredientsEntry.TABLE_NAME,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//
//            case CODE_SANDWICHES: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.SandwichEntry.TABLE_NAME,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//
//            case CODE_PROMO: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.PromoEntry.TABLE_NAME,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//
//            case CODE_ORDERS: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.OrdersEntry.TABLE_NAME,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//
//            case CODE_INGREDIENTS_FROM_SANDWICH_ID: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.SandwichIngredientsEntry.TABLE_NAME,
//                        projection,
//                        LanchoneteContract.SandwichIngredientsEntry.COLUMN_SANDWICH_ID + " = ? ",
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//
//            case CODE_SANDWICH_WITH_ID: {
//                cursor = mOpenHelper.getReadableDatabase().query(
//                        LanchoneteContract.SandwichIngredientsEntry.TABLE_NAME,
//                        projection,
//                        LanchoneteContract.SandwichEntry.COLUMN_SANDWICH_ID + " = ? ",
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//                break;
//            }
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
//
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);
//        return cursor;
//    }
//
//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        final int match = sUriMatcher.match(uri);
//        Uri returnUri = null;
//
//        switch (sUriMatcher.match(uri)) {
//            case CODE_ORDER_INSERTION: {
//                long _id = mOpenHelper.getWritableDatabase().insert(
//                        LanchoneteContract.OrdersEntry.TABLE_NAME, null, values);
//                if (_id > 0) {
//                    returnUri = LanchoneteContract.OrdersEntry.buildOrderUri(_id);
//                } else {
//                    throw new android.database.SQLException("Failed to insert row into " + uri);
//                }
//                break;
//            }
//        }
//        return returnUri;
//    }
//
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//        return 0;
//    }
//
//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
//        return 0;
//    }
//}
