package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

public class Image extends MediaResourse {
    public Image(String name, Catalog catalog){
        super(TypeMedia.IMAGE, name, catalog);
    }

    @Override
    public String toString() {
        return getName() + " (image)";
    }
}
