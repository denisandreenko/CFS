package com.exposit_ds.www;

import com.exposit_ds.www.catalogDescription.Catalog;
import com.exposit_ds.www.mediaDescription.MediaResourse;


public interface CollectionManager<T> {

    void add(T media);

    void delete(String name);

    void show(Catalog currentCatalog);

    void addFavorites(String name);

    void showFavorites();

    void deleteFavorites(String name);

    MediaResourse findForEdit(String name);

}
