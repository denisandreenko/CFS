package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

public interface MediaManager<T> {

    boolean add(T media);

    boolean delete(String name, Catalog currentCatalog);

    boolean show(Catalog catalog);

    void addFavorites(String name, Catalog currentCatalog);

    void showFavorites();

    boolean deleteFavorites(String name, Catalog currentCatalog);

    void search(MediaResource media, TypeMedia typeMedia);

    MediaResource findForEdit(String name, Catalog currentCatalog);
}
