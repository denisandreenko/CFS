package com.exposit_ds.www;

import com.exposit_ds.www.catalogDescription.CatalogCollection;
import com.exposit_ds.www.mediaDescription.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

public class PlayMediaCatalog {

    public static final String TEMP_OUT_MEDIA = "tempMedia.out";
    public static final String TEMP_OUT_CATALOGS = "tempCatalogs.out";
    private static Map<String, CommandManager> mapCommands = new HashMap<>();
    private static MediaManager<MediaResource> collection = new MediaCollection<>();
    private static Logger log = Logger.getLogger(PlayMediaCatalog.class.getName());
    private static CatalogCollection catalogCollection = new CatalogCollection();

    public static void playMediaCatalog() throws FileNotFoundException {

        System.out.println("Enter 'help' to see a list of commands");
        catalogCollection.add("MediaCatalog", null);
        catalogCollection.setCurrentCatalog(catalogCollection.getListCatalog().get(0));

        Scanner input = new Scanner(System.in);
        while (true) {
            command();
            String key = input.nextLine();
            if (mapCommands.containsKey(key)) {
                mapCommands.get(key).runCommand(input);
            } else {
                System.out.println("incorrect command");
            }
        }
    }

    public static void command() throws FileNotFoundException {

        mapCommands.put("help", input -> help());

        mapCommands.put("add -m", input -> {
            String choose = selectMedia(input);
            switch (choose) {
                case "1":
                    addMedia(input, TypeMedia.VIDEO);
                    break;
                case "2":
                    addMedia(input, TypeMedia.AUDIO);
                    break;
                case "3":
                    addMedia(input, TypeMedia.BOOK);
                    break;
                case "4":
                    addMedia(input, TypeMedia.IMAGE);
                    break;
                default:
                    System.out.println("incorrect input, repeat attempt");
                    break;
            }
        });

        mapCommands.put("edit -m", input -> {
            String name = getName(input);
            MediaResource media = collection.findForEdit(name, catalogCollection.getCurrentCatalog());
            setObject(input, media);
        });

        mapCommands.put("delete -m", input -> {
            String name = getName(input);
            collection.delete(name, catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("add -f", input -> {
            String name = getName(input);
            collection.addFavorites(name, catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("show -f", input -> collection.showFavorites());

        mapCommands.put("delete -f", input -> {
            String name = getName(input);
            collection.deleteFavorites(name, catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("add -c", input -> {
            String name = getName(input);
            if (catalogCollection.add(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
            } else {
                System.out.println("directory with that name already exists");
            }
        });

        mapCommands.put("edit -c", input -> {
            String name = getName(input);
            String newName = getNewName(input);
            catalogCollection.edit(name, newName, catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("show", input -> {
            if (!catalogCollection.show(catalogCollection.getCurrentCatalog())
                    && !collection.show(catalogCollection.getCurrentCatalog())) {
                System.out.println("is empty");
            }
        });

        mapCommands.put("delete -c", input -> {
            String name = getName(input);
            if (catalogCollection.delete(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
            } else {
                System.out.println("directory with that name is not found");
            }
        });

        mapCommands.put("move", input -> {
            String name = getName(input);
            catalogCollection.move(name);
        });

        mapCommands.put("back", input -> catalogCollection.back());

        mapCommands.put("search", input -> {
            String choose = selectMedia(input);
            switch (choose) {
                case "1":
                    searchMedia(input, TypeMedia.VIDEO);
                    break;
                case "2":
                    searchMedia(input, TypeMedia.AUDIO);
                    break;
                case "3":
                    searchMedia(input, TypeMedia.BOOK);
                    break;
                case "4":
                    searchMedia(input, TypeMedia.IMAGE);
                    break;
                default:
                    System.out.println("incorrect input, repeat attempt");
                    break;
            }
        });

        mapCommands.put("save", input -> {
//            save(catalogCollection, TEMP_OUT_CATALOGS);
//            save(collection, TEMP_OUT_MEDIA);
            if (save(catalogCollection, TEMP_OUT_CATALOGS) && save(collection, TEMP_OUT_MEDIA)) {
                sayDone();
            }
        });

        mapCommands.put("read", input -> {
            catalogCollection = (CatalogCollection) read(TEMP_OUT_CATALOGS);
            collection = (MediaCollection<MediaResource>) read(TEMP_OUT_MEDIA);
            if (catalogCollection != null && collection != null) {
                sayDone();
            }
        });

        mapCommands.put("exit", input -> System.exit(0));
    }

    private static void sayDone() {
        System.out.println("done");
    }

    public static boolean save(Object object, String fileName) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (IOException ex) {
            System.out.println("when saving data error occurred");
            return false;
        }
        return true;
    }

    public static Object read(String fileName) {
        Object object = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String a = bufferedReader.readLine();
            if (a != null) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
                object = objectInputStream.readObject();
                objectInputStream.close();
            } else {
                System.out.println("file is empty");
            }
            bufferedReader.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("an error occurred while reading file");
        }
        return object;
    }

    public static List<Field> getInheritedPrivateFieldss(Class<?> type) {
        List<Field> result = new ArrayList<Field>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            for (Field field : i.getDeclaredFields()) {
                result.add(field);
            }
            i = i.getSuperclass();
        }
        return result;
    }

    private static void setObject(Scanner input, MediaResource media) {
        Class<? extends MediaResource> mediaClass = media.getClass();
        List<Field> inheritedPrivateFieldss = getInheritedPrivateFieldss(mediaClass);
        for (int i = inheritedPrivateFieldss.size()-1; i >= 0; i--) {
            MediaInfo mediaInfo = inheritedPrivateFieldss.get(i).getAnnotation(MediaInfo.class);
            if (mediaInfo == null) {
                continue;
            }
            System.out.print(mediaInfo.name());
            String nextLine = input.nextLine();
            inheritedPrivateFieldss.get(i).setAccessible(true);
            try {
                inheritedPrivateFieldss.get(i).set(media, nextLine);
            } catch (IllegalAccessException e) {
                System.out.println("set error");
            }
        }
    }

    private static void searchMedia(Scanner input, TypeMedia typeMedia) {
        if (typeMedia == TypeMedia.VIDEO) {
            Video video = new Video();
            setObject(input, video);
            collection.search(video, video.getType());
        } else if (typeMedia == TypeMedia.AUDIO) {
            Audio audio = new Audio();
            setObject(input, audio);
            collection.search(audio, audio.getType());
        } else if (typeMedia == TypeMedia.BOOK) {
            Book book = new Book();
            setObject(input, book);
            collection.search(book, book.getType());
        } else if (typeMedia == TypeMedia.IMAGE) {
            Image image = new Image();
            setObject(input, image);
            collection.search(image, image.getType());
        }
    }

    //Need fix if int value
    private static void addMedia(Scanner input, TypeMedia typeMedia) {
        if (typeMedia == TypeMedia.VIDEO) {
            Video video = new Video();
            setObject(input, video);
            video.setExternalCatalog(catalogCollection.getCurrentCatalog());
            collection.add(video);
        } else if (typeMedia == TypeMedia.AUDIO) {
            Audio audio = new Audio();
            setObject(input, audio);
            audio.setExternalCatalog(catalogCollection.getCurrentCatalog());
            collection.add(audio);
        } else if (typeMedia == TypeMedia.BOOK) {
            Book book = new Book();
            setObject(input, book);
            book.setExternalCatalog(catalogCollection.getCurrentCatalog());
            collection.add(book);
        } else if (typeMedia == TypeMedia.IMAGE) {
            Image image = new Image();
            setObject(input, image);
            image.setExternalCatalog(catalogCollection.getCurrentCatalog());
            collection.add(image);
        }
    }

    private static String selectMedia(Scanner input) {
        System.out.println("select number:");
        System.out.println("1.Video");
        System.out.println("2.Audio");
        System.out.println("3.Book");
        System.out.println("4.Image");

        return input.nextLine();
    }

    private static String getName(Scanner input) {
        System.out.print("name: ");
        return input.nextLine();
    }

    private static String getNewName(Scanner input) {
        System.out.print("new name: ");
        return input.nextLine();
    }

    public static void help() {
        System.out.println("add -m  - add a media resource");
        System.out.println("edit -m  - edit a media resource");
        System.out.println("delete -m  - delete a media resource");
        System.out.println("add -f  - add a resource in \"favorite\"");
        System.out.println("show -f  - show \"favorite\" ");
        System.out.println("delete -f  - delete a resource from \"favorite\"");
        System.out.println("add -c  - add a directory");
        System.out.println("edit -c  - edit a directory");
        System.out.println("delete -c  - delete a directory");
        System.out.println("move - move to the directory");
        System.out.println("back - back to the previous directory");
        System.out.println("show  - show the resources of the current directory");
        System.out.println("search  - search a media resource");
        System.out.println("save - save media catalog data");
        System.out.println("read - read media catalog data");
        System.out.println("exit - exit application");
    }
}
