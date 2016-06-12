package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

public class Audio extends MediaResourse {
    private String nameSinger;

    public Audio(String name, Catalog catalog, String nameSinger){
        super(TypeMedia.AUDIO, name, catalog);
        this.nameSinger = nameSinger;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    @Override
    public String toString() {
        return getNameSinger() + " - " + getName() + " (audio)";
    }
}
