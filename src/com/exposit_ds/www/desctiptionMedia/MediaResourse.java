package com.exposit_ds.www.desctiptionMedia;

/**
 * Created by knigi on 07.06.2016.
 */
public class MediaResourse {

    private String name;
    private String year;
    private String nameActor;

    public MediaResourse(String nameMovie, String year, String nameActor)
    {
        this.name = nameMovie;
        this.year = year;
        this.nameActor = nameActor;
    }

    public String getNameMovie() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getNameActor() {
        return nameActor;
    }

}
