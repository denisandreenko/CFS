package com.exposit_ds.www.mediaDescription;

public class Video extends MediaResourse {
    private int year;

    public Video(String name, int year) {
        super(TypeMedia.VIDEO, name);
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


