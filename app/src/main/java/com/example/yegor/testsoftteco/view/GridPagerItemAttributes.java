package com.example.yegor.testsoftteco.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import com.example.yegor.testsoftteco.Utils;

public class GridPagerItemAttributes implements Parcelable {

    public static final Creator<GridPagerItemAttributes> CREATOR = new Creator<GridPagerItemAttributes>() {
        @Override
        public GridPagerItemAttributes createFromParcel(Parcel source) {
            return new GridPagerItemAttributes(source);
        }

        @Override
        public GridPagerItemAttributes[] newArray(int size) {
            return new GridPagerItemAttributes[size];
        }
    };

    @DrawableRes
    private int image;
    private String text;
    private int id;

    public GridPagerItemAttributes() {
    }

    public GridPagerItemAttributes(int image, String text) {

        this.image = image;
        this.text = text;
        id = Utils.generateViewId();
    }


    protected GridPagerItemAttributes(Parcel in) {

        this.image = in.readInt();
        this.text = in.readString();
        this.id = in.readInt();
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.text);
        dest.writeInt(this.id);
    }

    @Override
    public String toString() {
        return "ItemParam{" +
                "image=" + image +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }

}
