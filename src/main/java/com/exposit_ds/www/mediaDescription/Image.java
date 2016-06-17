package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Image extends MediaResource implements Serializable{

    public Image(String name, Catalog catalog){
        super(TypeMedia.IMAGE, name, catalog);
    }

    public Image() {
        super(TypeMedia.IMAGE);
    }

    @Override
    public String toString() {
        return getName() + " (image)";
    }
}
