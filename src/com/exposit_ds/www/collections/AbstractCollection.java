package com.exposit_ds.www.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AbstractCollection<T> implements Serializable{

    private List<T> listMedia = new ArrayList<>();

    public void add(T media) {
        listMedia.add(media);
    }

}

