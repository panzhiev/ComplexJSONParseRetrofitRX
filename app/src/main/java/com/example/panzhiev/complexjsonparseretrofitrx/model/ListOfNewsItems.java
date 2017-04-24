package com.example.panzhiev.complexjsonparseretrofitrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfNewsItems {

    @SerializedName("NewsItem")
    @Expose
    private List<NewsItem> listOfNewsItems = null;

    public List<NewsItem> getListOfNewsItems() {
        return listOfNewsItems;
    }

    public void setListOfNewsItems(List<NewsItem> listOfNewsItems) {
        this.listOfNewsItems = listOfNewsItems;
    }
}
