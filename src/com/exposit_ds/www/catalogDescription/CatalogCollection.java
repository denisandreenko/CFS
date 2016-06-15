package com.exposit_ds.www.catalogDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogCollection implements Serializable, CatalogManager {

    private List<Catalog> listCatalog = new ArrayList<>();
    private Catalog currentCatalog;

    public void add(String name, Catalog externalCatalog) {
        listCatalog.add(new Catalog(name, externalCatalog));
    }

    public void delete(String name) {
        Iterator<Catalog> it = listCatalog.iterator();
        while (it.hasNext()) {
            Catalog item = it.next();
            if (item.getNameCatalog().equals(name)) {
                it.remove();
            }
        }
    }

    public void edit(String name, String newName) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getNameCatalog().equals(name)) {
                catalog.setNameCatalog(newName);
            }
        }
    }

    public void show(Catalog currentCatalog) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getExternalCatalog() == null) {
                continue;
            }
            else if (catalog.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(catalog);
            }
        }
    }

    public void move(String name) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getNameCatalog().equals(name)) {
                currentCatalog = catalog;
            }
        }
    }

    public void back() {
        currentCatalog = currentCatalog.getExternalCatalog();
    }

    public Catalog getCurrentCatalog() {
        return currentCatalog;
    }

    public void setCurrentCatalog(Catalog currentCatalog) {
        this.currentCatalog = currentCatalog;
    }

    public List<Catalog> getListCatalog() {
        return listCatalog;
    }
}
