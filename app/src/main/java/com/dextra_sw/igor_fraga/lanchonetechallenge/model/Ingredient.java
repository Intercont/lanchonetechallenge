package com.dextra_sw.igor_fraga.lanchonetechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by intercont on 26/06/17.
 */

public class Ingredient implements Parcelable {

    private int id;
    private String name;
    private double price;
    private String imageUrl;
    private int quantity = 1;

    public Ingredient(){
    }

    protected Ingredient(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        imageUrl = in.readString();
        quantity = in.readInt();
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(imageUrl);
        dest.writeInt(quantity);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}