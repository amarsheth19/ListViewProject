package com.example.customadapterproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Athletes implements Parcelable {

    private String name;
    private int age;
    private int image;
    private String description;
    private int draftYear;

    public Athletes(String n, int a, int i, int dy, String d){

        name = n;
        age = a;
        image = i;
        description = d;
        draftYear = dy;

    }

    protected Athletes(Parcel in) {
        name = in.readString();
        age = in.readInt();
        image = in.readInt();
        description = in.readString();
        draftYear = in.readInt();
    }

    public static final Creator<Athletes> CREATOR = new Creator<Athletes>() {
        @Override
        public Athletes createFromParcel(Parcel in) {
            return new Athletes(in);
        }

        @Override
        public Athletes[] newArray(int size) {
            return new Athletes[size];
        }
    };

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);
        out.writeInt(age);
        out.writeInt(image);
        out.writeString(description);
        out.writeInt(draftYear);
    }

    public int getDraftYear() {
        return draftYear;
    }

    public String getDescription(){
        return description;
    }

    public int getAge() {
        return age;
    }

    public String getName(){
        return name;
    }

    public int getImage(){
        return  image;
    }





}
