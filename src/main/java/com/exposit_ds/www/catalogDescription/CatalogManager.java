package com.exposit_ds.www.catalogDescription;

public interface CatalogManager {

    void add(String name, Catalog currentCatalog);

    void delete(String name, Catalog currentCatalog);

    boolean show(Catalog catalog);

    void edit(String name, String newName, Catalog currentCatalog);

    void move(String name);

    void back();
}
