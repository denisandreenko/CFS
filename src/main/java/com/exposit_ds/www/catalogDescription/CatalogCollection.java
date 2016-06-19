package com.exposit_ds.www.catalogDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogCollection implements Serializable, CatalogManager {

    private List<Catalog> listCatalog = new ArrayList<>();
    private List<Catalog> locate = new ArrayList<>();
    private Catalog currentCatalog;

    public CatalogCollection() {
        listCatalog.add(new Catalog("root", null));
        currentCatalog = listCatalog.get(0);
        locate.add(listCatalog.get(0));
    }

    @Override
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

    @Override
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

    @Override
    public boolean edit(String name, String newName, Catalog currentCatalog) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getNameCatalog().equals(name) && catalog.getExternalCatalog().equals(currentCatalog)) {
                catalog.setNameCatalog(newName);
                return true;
            }
        }
        return false;
    }

    @Override
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

    @Override
    public boolean move(String name, Catalog curCatalog) {
        for (Catalog catalog : listCatalog) {
            if (catalog.getNameCatalog().equals(name) && catalog.getExternalCatalog().equals(curCatalog)) {
                currentCatalog = catalog;
                locate.add(currentCatalog);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean back() {
        if (currentCatalog.getExternalCatalog() == null) {
            return false;
        } else {
            currentCatalog = currentCatalog.getExternalCatalog();
            locate.remove(locate.size()-1);
            return true;
        }
    }

    public void showLocate() {
        System.out.print(locate.get(0).getNameCatalog());
        for (int i = 1; i < locate.size(); i++) {
            System.out.print("\\"+locate.get(i).getNameCatalog());
        }
        System.out.print("> ");
    }



    public Catalog getCurrentCatalog() {
        return currentCatalog;
    }
}
