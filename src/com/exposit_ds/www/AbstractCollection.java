package com.exposit_ds.www;

import com.exposit_ds.www.catalogDescription.Catalog;
import com.exposit_ds.www.mediaDescription.MediaResourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AbstractCollection<T extends MediaResourse> implements Serializable, CollectionManager<T>{

    private List<T> listMedia = new ArrayList<>();

    public void add(T media) {
        listMedia.add(media);
    }

    public void delete(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                listMedia.remove(media);
            }
        }
    }

    public void show(Catalog currentCatalog) {
        for (T media : listMedia) {
            if (media.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(media);
            }
        }
    }

    public void addFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(true);
            }
        }
    }

    public void showFavorites() {
        int i = 1;
        for (T media : listMedia) {
            if (media.getFavorites() == true){
                System.out.print(i++ + ". ");
                System.out.println(media);
            }
        }
    }

    public void deleteFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(false);
            }
        }
    }

    public MediaResourse findForEdit(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                return media;
            }
        }
        return null;
    }
}

