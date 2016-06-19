package com.exposit.www.catalogDescription;

public interface CatalogManager {

    boolean add(String name, Catalog currentCatalog);

    boolean delete(String name, Catalog currentCatalog);

    boolean show(Catalog catalog);

    boolean edit(String name, String newName, Catalog currentCatalog);

    boolean move(String name, Catalog currentCatalog);

    boolean back();
}
