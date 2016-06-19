package com.exposit.www.mediaDescription;

import com.exposit.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Book extends MediaResource implements Serializable {

    @MediaInfo(name = "year: ")
    private Integer year;

    @MediaInfo(name = "name author: ")
    private String nameAuthor;

    public Book(String name, Catalog catalog, Integer year, String nameAuthor){
        super(TypeMedia.BOOK, name, catalog);
        this.year = year;
        this.nameAuthor = nameAuthor;
    }

    public Book() {
        super(TypeMedia.BOOK);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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
