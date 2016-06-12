package com.exposit_ds.www.catalogDescription;

import java.io.Serializable;

public class Catalog implements Serializable {
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

    public void setExternalCatalog(Catalog externalCatalog) {
        this.externalCatalog = externalCatalog;
    }

    @Override
    public String toString() {
        return getNameCatalog();
    }
}
