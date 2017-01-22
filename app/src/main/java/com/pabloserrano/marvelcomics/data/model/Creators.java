
package com.pabloserrano.marvelcomics.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creators implements Parcelable{

    private int available;
    private String collectionURI;
    private List<Item> items = null;
    private int returned;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Creators(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
        returned = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeString(collectionURI);
        dest.writeTypedList(items);
        dest.writeInt(returned);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Creators> CREATOR = new Creator<Creators>() {
        @Override
        public Creators createFromParcel(Parcel in) {
            return new Creators(in);
        }

        @Override
        public Creators[] newArray(int size) {
            return new Creators[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Creators{" +
                "available=" + available +
                ", collectionURI='" + collectionURI + '\'' +
                ", items=" + items +
                ", returned=" + returned +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
