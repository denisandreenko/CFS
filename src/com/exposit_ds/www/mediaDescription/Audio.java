package com.exposit_ds.www.mediaDescription;

public class Audio extends MediaResourse{
    private String nameSinger;

    public Audio(String name, String nameSinger){
        super(TypeMedia.AUDIO, name);
        this.nameSinger = nameSinger;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }
}
