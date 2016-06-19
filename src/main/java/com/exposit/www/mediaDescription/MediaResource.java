package com.exposit.www.mediaDescription;


import com.exposit.www.catalogDescription.Catalog;

import java.io.Serializable;

public abstract class MediaResource implements Serializable {

    @MediaInfo(name = "name: ")
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

    public MediaResource(TypeMedia type) {
        this.type = type;
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

    public void setExternalCatalog(Catalog externalCatalog) {
        this.externalCatalog = externalCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediaResource that = (MediaResource) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        if (favorites != null ? !favorites.equals(that.favorites) : that.favorites != null) {
            return false;
        }
        return !(externalCatalog != null ? !externalCatalog.equals(that.externalCatalog) : that.externalCatalog != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (favorites != null ? favorites.hashCode() : 0);
        result = 31 * result + (externalCatalog != null ? externalCatalog.hashCode() : 0);
        return result;
    }
}
