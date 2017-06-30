package com.dextra_sw.igor_fraga.lanchonetechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by intercont on 26/06/17.
 */

public class Sandwich implements Parcelable {

    private int id;
    private String name;
    private ArrayList<Ingredient> ingredients;
    private String ingredientsListed;
    private String imageUrl;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredientsListed() {
        return ingredientsListed;
    }

    public void setIngredientsListed(String ingredientsListed) {
        this.ingredientsListed = ingredientsListed;
    }

    public Sandwich(){}

    protected Sandwich(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<Ingredient>();
            in.readList(ingredients, Ingredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
        ingredientsListed = in.readString();
        imageUrl = in.readString();
        price = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
        dest.writeString(ingredientsListed);
        dest.writeString(imageUrl);
        dest.writeDouble(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Sandwich> CREATOR = new Parcelable.Creator<Sandwich>() {
        @Override
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        @Override
        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };
}