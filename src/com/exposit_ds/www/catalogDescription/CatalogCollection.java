package com.exposit_ds.www.catalogDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatalogCollection implements Serializable {
    private List<Catalog> listCatalog = new ArrayList<>();
    private Catalog currentCatalog = new Catalog("MediaCatalog", null);

    public void add(String name, Catalog externalCatalog) {
        listCatalog.add(new Catalog(name, externalCatalog));
    }

    public void show(Catalog currentCatalog) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(catalog);
            }
        }
    }

    public Catalog getCurrentCatalog() {
        return currentCatalog;
    }

    public void setCurrentCatalog(Catalog currentCatalog) {
        this.currentCatalog = currentCatalog;
    }

}
