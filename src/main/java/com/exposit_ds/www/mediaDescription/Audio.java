package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;

public class Audio extends MediaResource implements Serializable{

    @MediaInfo(name = "name singer: ")
    private String nameSinger;

    public Audio(String name, Catalog catalog, String nameSinger){
        super(TypeMedia.AUDIO, name, catalog);
        this.nameSinger = nameSinger;
    }

    public Audio() {
        super(TypeMedia.AUDIO);
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
