package com.exposit_ds.www.catalogDescription;

import com.exposit_ds.www.mediaDescription.MediaInfo;

import java.io.Serializable;

public class Catalog implements Serializable {

    @MediaInfo(name = "name: ")
    private String nameCatalog;
    private Catalog externalCatalog;

    public Catalog(String nameCatalog, Catalog externalCatalog) {
        this.nameCatalog = nameCatalog;
        this.externalCatalog = externalCatalog;
    }

    public String getNameCatalog() {
        return nameCatalog;
    }

    public void setNameCatalog(String nameCatalog) {
        this.nameCatalog = nameCatalog;
    }

    public Catalog getExternalCatalog() {
        return externalCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (nameCatalog != null ? !nameCatalog.equals(catalog.nameCatalog) : catalog.nameCatalog != null) return false;
        return !(externalCatalog != null ? !externalCatalog.equals(catalog.externalCatalog) : catalog.externalCatalog != null);

    }

    @Override
    public int hashCode() {
        int result = nameCatalog != null ? nameCatalog.hashCode() : 0;
        result = 31 * result + (externalCatalog != null ? externalCatalog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getNameCatalog() + " (catalog)";
    }
}
