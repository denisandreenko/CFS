package com.exposit_ds.www.mediaDescription;


import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class MediaResource implements Serializable {

    private String name;
    private TypeMedia type;
    private Boolean favorites = false;
    private Catalog externalCatalog;

    public MediaResource(TypeMedia type, String name, Catalog externalCatalog)
    {
        this.type = type;
        this.name = name;
        this.externalCatalog = externalCatalog;
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

    public Boolean getFavorites() {
        return favorites;
    }

    public void setFavorites(Boolean favorites) {
        this.favorites = favorites;
    }

    public Catalog getExternalCatalog() {
        return externalCatalog;
    }
}
