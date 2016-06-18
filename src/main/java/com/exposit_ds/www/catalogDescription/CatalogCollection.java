package com.exposit_ds.www.catalogDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogCollection implements Serializable, CatalogManager {

    private List<Catalog> listCatalog = new ArrayList<>();
    private Catalog currentCatalog;

    public boolean add(String name, Catalog currentCatalog) {
        if (listCatalog.isEmpty()) {
            listCatalog.add(new Catalog(name, currentCatalog));
        } else {
            for (Catalog catalog : listCatalog) {
                if (catalog.getNameCatalog().equals(name) && catalog.getExternalCatalog().equals(currentCatalog)) {
                    return false;
                }
            }
            listCatalog.add(new Catalog(name, currentCatalog));
        }
        return true;
    }

    public boolean delete(String name, Catalog currentCatalog) {
        Iterator<Catalog> it = listCatalog.iterator();
        while (it.hasNext()) {
            Catalog item = it.next();
            if (item.getNameCatalog().equals(name) && item.getExternalCatalog().equals(currentCatalog)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void edit(String name, String newName, Catalog currentCatalog) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getNameCatalog().equals(name) && catalog.getExternalCatalog().equals(currentCatalog)) {
                catalog.setNameCatalog(newName);
            }
        }
    }

    public boolean show(Catalog currentCatalog) {
        int count = 0;
        for (Catalog catalog : listCatalog) {
            if (catalog.getExternalCatalog() == null) {
                continue;
            }
            else if (catalog.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(catalog);
                count++;
            }
        }
        return count > 0;
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
