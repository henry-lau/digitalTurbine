package com.example.digitalturbine.data.model.db.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ad", strict = false)
public class ad implements Parcelable {

    public ad() {
    }

    public ad(String appId, String productName) {
        this.appId = appId;
        this.productName = productName;
    }

    @Element(name = "appId")
    public String appId;

    @Element(name = "averageRatingImageURL")
    public String averageRatingImageURL;

    @Element(name = "bidRate")
    public String bidRate;

    @Element(name = "categoryName")
    public String categoryName;

    @Element(name = "numberOfRatings")
    public String numberOfRatings;

    @Element(name = "productDescription")
    public String productDescription;

    @Element(name = "productName")
    public String productName;

    @Element(name = "productThumbnail")
    public String productThumbnail;

    @Element(name = "rating")
    public String rating;

    @Element(name = "numberOfDownloads")
    public String numberOfDownloads;

    protected ad(Parcel in) {
        appId = in.readString();
        averageRatingImageURL = in.readString();
        bidRate = in.readString();
        categoryName = in.readString();
        numberOfRatings = in.readString();
        productDescription = in.readString();
        productName = in.readString();
        productThumbnail = in.readString();
        rating = in.readString();
        numberOfDownloads = in.readString();
    }

    public static final Creator<ad> CREATOR = new Creator<ad>() {
        @Override
        public ad createFromParcel(Parcel in) {
            return new ad(in);
        }

        @Override
        public ad[] newArray(int size) {
            return new ad[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appId);
        parcel.writeString(averageRatingImageURL);
        parcel.writeString(bidRate);
        parcel.writeString(categoryName);
        parcel.writeString(numberOfRatings);
        parcel.writeString(productDescription);
        parcel.writeString(productName);
        parcel.writeString(productThumbnail);
        parcel.writeString(rating);
        parcel.writeString(numberOfDownloads);
    }
}
