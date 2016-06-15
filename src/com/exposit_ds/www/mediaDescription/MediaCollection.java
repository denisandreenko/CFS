package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MediaCollection<T extends MediaResource> implements Serializable, MediaManager<T> {

    private List<T> listMedia = new ArrayList<>();

    @Override
    public void add(T media) {
        listMedia.add(media);
    }

    @Override
    public void delete(String name) {
        Iterator<T> it = listMedia.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.getName().equals(name)) {
                deleteFavorites(name);
                it.remove();
            }
        }
    }

    @Override
    public void show(Catalog currentCatalog) {
        for (T media : listMedia) {
            if (media.getExternalCatalog() == null) {
                continue;
            }
            if (media.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(media);
            }
        }
    }

    @Override
    public void addFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(true);
            }
        }
    }

    @Override
    public void showFavorites() {
        for (T media : listMedia) {
            if (media.getFavorites()){
                System.out.println(media);
            }
        }
    }

    @Override
    public void deleteFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(false);
            }
        }
    }

    @Override
    public MediaResource findForEdit(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                return media;
            }
        }
        return null;
    }
}

