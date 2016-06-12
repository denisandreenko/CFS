package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

public class Video extends MediaResourse {
    private int year;

    public Video(String name, Catalog catalog, int year) {
        super(TypeMedia.VIDEO, name, catalog);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return getName()+ " (" + getYear() + ") " + "(video)";
    }
}


