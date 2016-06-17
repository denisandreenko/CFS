package com.exposit_ds.www.mediaDescription;

import com.exposit_ds.www.catalogDescription.Catalog;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.exposit_ds.www.PlayMediaCatalog.getInheritedPrivateFieldss;


public class MediaCollection<T extends MediaResource> implements Serializable, MediaManager<T> {

    private List<T> listMedia = new ArrayList<>();

    @Override
    public void add(T media) {
        listMedia.add(media);
    }

    @Override
    public void delete(String name) {
        Iterator<T> it = listMedia.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.getName().equals(name)) {
                deleteFavorites(name);
                it.remove();
            }
        }
    }

    @Override
    public void show(Catalog currentCatalog) {
        for (T media : listMedia) {
            if (media.getExternalCatalog() == null) {
                continue;
            }
            if (media.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(media);
            }
        }
    }

    @Override
    public void addFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(true);
            }
        }
    }

    @Override
    public void showFavorites() {
        for (T media : listMedia) {
            if (media.getFavorites()) {
                System.out.println(media);
            }
        }
    }

    @Override
    public void deleteFavorites(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                media.setFavorites(false);
            }
        }
    }

    @Override
    public void search(MediaResource comparableObject, TypeMedia typeMedia) {
        for (MediaResource media : listMedia) {
            if (media.getType().equals(typeMedia)) {
                try {
                    if ((compare(media, comparableObject, false))) {
                        System.out.println(media);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean compare(MediaResource mediaResource, MediaResource comparableMediaResource, boolean isStrict) throws IllegalAccessException {
        int count = 0;
        Class<? extends MediaResource> mediaClass = mediaResource.getClass();
        List<Field> inheritedPrivateFieldss = getInheritedPrivateFieldss(mediaClass);
        for (int i = inheritedPrivateFieldss.size()-1; i >= 0; i--) {
            MediaInfo mediaInfo = inheritedPrivateFieldss.get(i).getAnnotation(MediaInfo.class);
            if (mediaInfo == null) {
                continue;
            }
            inheritedPrivateFieldss.get(i).setAccessible(true);
            boolean isEquals = inheritedPrivateFieldss.get(i).get(mediaResource).equals(inheritedPrivateFieldss.get(i).get(comparableMediaResource));
            if (inheritedPrivateFieldss.get(i).get(comparableMediaResource) == null) {
                count++;
                continue;
            }
            if (!isEquals && isStrict) {
                return false;
            } else if (isEquals && !isStrict) {
                return true;
            }
            count++;
        }

        if (isStrict && count == mediaResource.getClass().getDeclaredFields().length) {
            return true;
        }
        return false;
    }

    @Override
    public MediaResource findForEdit(String name) {
        for (T media : listMedia) {
            if (media.getName().equals(name)) {
                return media;
            }
        }
        return null;
    }
}

