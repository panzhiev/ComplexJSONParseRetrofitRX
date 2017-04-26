package com.example.panzhiev.complexjsonparseretrofitrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsImage implements Serializable{

    @SerializedName("Photo")
    @Expose
    private String mPhoto;
    @SerializedName("Thumb")
    @Expose
    private String mThumb;
    @SerializedName("PhotoCaption")
    @Expose
    private String mPhotoCaption;

    public NewsImage (String mPhoto, String mThumb, String mPhotoCaption) {
        this.mPhoto = mPhoto;
        this.mThumb = mThumb;
        this.mPhotoCaption = mPhotoCaption;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        this.mPhoto = photo;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        this.mThumb = thumb;
    }

    public String getPhotoCaption() {
        return mPhotoCaption;
    }

    public void setPhotoCaption(String photoCaption) {
        this.mPhotoCaption = photoCaption;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "mPhoto='" + mPhoto + '\'' +
                ", mThumb='" + mThumb + '\'' +
                ", mPhotoCaption='" + mPhotoCaption + '\'' +
                '}';
    }
}

