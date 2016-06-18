package com.exposit_ds.www.catalogDescription;

public interface CatalogManager {

    boolean add(String name, Catalog currentCatalog);

    boolean delete(String name, Catalog currentCatalog);

    boolean show(Catalog catalog);

    boolean edit(String name, String newName, Catalog currentCatalog);

    void move(String name);

    void back();
}
