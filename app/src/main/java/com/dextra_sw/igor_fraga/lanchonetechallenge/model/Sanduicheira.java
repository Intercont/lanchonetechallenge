package com.dextra_sw.igor_fraga.lanchonetechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intercont on 30/06/17.
 */

public class Sanduicheira implements Parcelable {
    List<Sandwich> allSandwiches;

    public List<Sandwich> getAllSandwiches() {
        return allSandwiches;
    }

    public void setAllSandwiches(List<Sandwich> allSandwiches) {
        this.allSandwiches = allSandwiches;
    }

    public Sanduicheira(){}

    public Sanduicheira(Parcel in) {
        if (in.readByte() == 0x01) {
            allSandwiches = new ArrayList<Sandwich>();
            in.readList(allSandwiches, Sandwich.class.getClassLoader());
        } else {
            allSandwiches = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (allSandwiches == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(allSandwiches);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Sanduicheira> CREATOR = new Parcelable.Creator<Sanduicheira>() {
        @Override
        public Sanduicheira createFromParcel(Parcel in) {
            return new Sanduicheira(in);
        }

        @Override
        public Sanduicheira[] newArray(int size) {
            return new Sanduicheira[size];
        }
    };
}