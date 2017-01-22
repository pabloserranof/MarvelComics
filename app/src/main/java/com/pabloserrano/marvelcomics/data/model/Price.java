
package com.pabloserrano.marvelcomics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Price implements Parcelable {

    private String type;
    private float price;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Price(Parcel in) {
        type = in.readString();
        price = in.readFloat();
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeFloat(price);
    }

    @Override
    public String toString() {
        return "Price{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
