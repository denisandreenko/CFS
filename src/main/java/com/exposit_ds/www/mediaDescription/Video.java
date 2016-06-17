package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Video extends MediaResource implements Serializable {
    @MediaInfo(name = "year: ")
    private String year;

    public Video(String name, Catalog catalog, String year) {
        super(TypeMedia.VIDEO, name, catalog);
        this.year = year;
    }

    public Video() {
        super(TypeMedia.VIDEO);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return getName()+ " (" + getYear() + ") " + "(video)";
    }
}


