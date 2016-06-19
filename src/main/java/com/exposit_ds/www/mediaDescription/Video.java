package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Video extends MediaResource implements Serializable {
    @MediaInfo(name = "year: ")
    private Integer year;

    public Video(String name, Catalog catalog, Integer year) {
        super(TypeMedia.VIDEO, name, catalog);
        this.year = year;
    }

    public Video() {
        super(TypeMedia.VIDEO);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return getName()+ " (" + getYear() + ") " + "(video)";
    }
}


