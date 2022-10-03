package com.example.numad22fa_shashankagrawal;

import android.os.Parcel;
import android.os.Parcelable;

public class Url implements Parcelable {
    private final String name;

    private final String link;

    /**
     * Constructs a person object with the specified name and age.
     *
     * @param name - name of URL
     * @param link -  link of URL
     */
    public Url(String name, String link) {
        this.name = name;
        this.link = link;
    }

    protected Url(Parcel in) {
        name = in.readString();
        link = in.readString();
    }

    public static final Creator<Url> CREATOR = new Parcelable.Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(link);
    }
}
