package com.exposit_ds.www;


import java.io.Serializable;

public class MediaResourse implements Serializable{

    private String name;
    private String year;
    private String nameAuthor;
    private TypeMedia type;


    public MediaResourse(String nameMovie, String year, String nameAuthor)
    {
        this.name = nameMovie;
        this.year = year;
        this.nameAuthor = nameAuthor;
    }

    public MediaResourse(String nameMovie)
    {
        this.name = nameMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNameActor() {
        return nameAuthor;
    }

    public void setNameActor(String nameActor) {
        this.nameAuthor = nameActor;
    }

    public TypeMedia getType() {
        return type;
    }

    public void setType(TypeMedia type) {
        this.type = type;
    }
}
