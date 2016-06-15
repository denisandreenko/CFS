package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Book extends MediaResource implements Serializable {

    private int year;
    private String nameAuthor;

    public Book(String name, Catalog catalog, int year, String nameAuthor){
        super(TypeMedia.BOOK, name, catalog);
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

    @Override
    public String toString() {
        return "\"" + getName() + "\" " + getYear() + " " + getNameAuthor() + " (book)";
    }
}
