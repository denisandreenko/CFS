package com.exposit_ds.www;

import com.exposit_ds.www.mediaDescription.MediaResourse;

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

        int i = 1;

        for (T media : listMedia) {

            System.out.print(i++ + ". ");
            System.out.println(media);

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

