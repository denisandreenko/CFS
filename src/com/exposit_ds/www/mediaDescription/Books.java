package com.exposit_ds.www.mediaDescription;

public class Books extends MediaResourse{
    private int year;
    private String nameAuthor;

    public Books(String name, int year, String nameAuthor){
        super(TypeMedia.BOOK, name);
        this.year = year;
        this.nameAuthor = nameAuthor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }
}
