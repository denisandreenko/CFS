package com.exposit_ds.www.mediaDescription;


import java.io.Serializable;

public class MediaResourse implements Serializable{

    private String name;
    private TypeMedia type;
    private Boolean favorites = false;

    public MediaResourse(TypeMedia type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMedia getType() {
        return type;
    }

    public void setType(TypeMedia type) {
        this.type = type;
    }

    public Boolean getFavorites() {
        return favorites;
    }

    public void setFavorites(Boolean favorites) {
        this.favorites = favorites;
    }
}
