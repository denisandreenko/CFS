package com.exposit_ds.www;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AbstractCollection<T extends MediaResourse> implements Serializable{

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

    public void show() {

        int i = 0;

        for (T a : listMedia) {

            System.out.println(i++ + " ");
            System.out.println(a);

        }
    }
}

