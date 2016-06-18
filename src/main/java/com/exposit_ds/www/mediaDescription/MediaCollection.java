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
    public boolean add(T media) {
        for (T mediaSource : listMedia) {
            if (mediaSource.getName().equals(media.getName()) && mediaSource.getExternalCatalog().equals(media.getExternalCatalog())) {
                return false;
            }
        }
        listMedia.add(media);
        return true;
    }

    @Override
    public boolean delete(String name, Catalog currentCatalog) {
        Iterator<T> it = listMedia.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.getName().equals(name) && item.getExternalCatalog().equals(currentCatalog)) {
                it.remove();
                if (item.getFavorites()) {
                    deleteFavorites(name, currentCatalog);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean show(Catalog currentCatalog) {
        int count = 0;
        for (T media : listMedia) {
            if (media.getExternalCatalog() == null) {
                continue;
            }
            if (media.getExternalCatalog().equals(currentCatalog)) {
                System.out.println(media);
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public void addFavorites(String name, Catalog currentCatalog) {
        int count = 0;
        for (T media : listMedia) {
            if (media.getName().equals(name) && media.getExternalCatalog().equals(currentCatalog)) {
                media.setFavorites(true);
                count++;
                System.out.println("done");
            }
        }
        if (count == 0) {
            System.out.println("resource is not found");
        }
    }

    @Override
    public void showFavorites() {
        int count = 0;
        for (T media : listMedia) {
            if (media.getFavorites()) {
                System.out.println(media);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("is empty");
        }
    }

    @Override
    public boolean deleteFavorites(String name, Catalog currentCatalog) {
        for (T media : listMedia) {
            if (media.getName().equals(name) && media.getExternalCatalog().equals(currentCatalog)) {
                media.setFavorites(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public void search(MediaResource comparableObject, TypeMedia typeMedia) {   //make strict search
        for (MediaResource media : listMedia) {
            if (media.getType().equals(typeMedia)) {
                try {
                    if ((compare(media, comparableObject, false))) {
                        System.out.println(media);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("originate error");
                }
            }
        }
    }

    @Override
    public MediaResource findForEdit(String name, Catalog currentCatalog) {
        for (T media : listMedia) {
            if (media.getName().equals(name) && media.getExternalCatalog().equals(currentCatalog)) {
                return media;
            }
        }
        System.out.println("resource is not found");
        return null;
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
}

