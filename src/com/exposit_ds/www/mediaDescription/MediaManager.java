package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

public interface MediaManager<T> {

    void add(T media);

    void delete(String name);

    void show(Catalog catalog);

    void addFavorites(String name);

    void showFavorites();

    void deleteFavorites(String name);

    MediaResource findForEdit(String name);
}
