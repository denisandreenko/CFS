package com.exposit_ds.www.mediaDescription;

public class Images extends MediaResourse{
    public Images(String name){
        super(TypeMedia.IMAGE, name);
    }

    @Override
    public String toString() {
        return getName() + " (image)";
    }
}
