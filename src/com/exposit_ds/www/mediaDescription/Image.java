package com.exposit_ds.www.mediaDescription;

public class Image extends MediaResourse {
    public Image(String name){
        super(TypeMedia.IMAGE, name);
    }

    @Override
    public String toString() {
        return getName() + " (image)";
    }
}
