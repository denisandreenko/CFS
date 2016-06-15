package com.exposit_ds.www.catalogDescription;

public interface CatalogManager {

    void add(String name, Catalog catalog);

    void delete(String name);

    void show(Catalog catalog);

    void edit(String name, String newName);

    void move(String name);

    void back();
}
