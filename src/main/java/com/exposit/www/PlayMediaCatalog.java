package com.exposit.www;

import com.exposit.www.mediaDescription.*;
import com.exposit.www.catalogDescription.CatalogCollection;

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
                    Video video = new Video();
                    addMedia(input, video);
                    break;
                case "2":
                    Audio audio = new Audio();
                    addMedia(input, audio);
                    break;
                case "3":
                    Book book = new Book();
                    addMedia(input, book);
                    break;
                case "4":
                    Image image = new Image();
                    addMedia(input, image);
                    break;
                default:
                    System.out.println("wrong input format (example: 1");
                    break;
            }
        });

        mapCommands.put("edit -m", input -> {
            String name = getName(input);
            MediaResource media = collection.findForEdit(name, catalogCollection.getCurrentCatalog());
            if (media != null) {
                setObject(input, media);
                return;
            }
            sayMediaNotFound();
        });

        mapCommands.put("delete -m", input -> {
            String name = getName(input);
            if (collection.delete(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            sayMediaNotFound();
        });

        mapCommands.put("add -f", input -> {
            String name = getName(input);
            if (collection.addFavorites(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            sayMediaNotFound();
        });

        mapCommands.put("show -f", input -> {
            if (!collection.showFavorites()) {
                System.out.println("is empty");
            }
        });

        mapCommands.put("delete -f", input -> {
            String name = getName(input);
            if (collection.deleteFavorites(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            sayMediaNotFound();
        });

        mapCommands.put("add -c", input -> {
            String name = getName(input);
            if (catalogCollection.add(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            System.out.println("directory with that name already exists");
        });

        mapCommands.put("edit -c", input -> {
            String name = getName(input);
            String newName = getNewName(input);
            if (catalogCollection.edit(name, newName, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            sayDirectoryNotFound();
        });

        mapCommands.put("show", input -> {
            boolean a = catalogCollection.show(catalogCollection.getCurrentCatalog());
            boolean b = collection.show(catalogCollection.getCurrentCatalog());
            if (!a & !b) {
                System.out.println("is empty");
            }
        });

        mapCommands.put("delete -c", input -> {
            String name = getName(input);
            if (catalogCollection.delete(name, catalogCollection.getCurrentCatalog())) {
                sayDone();
                return;
            }
            sayDirectoryNotFound();
        });

        mapCommands.put("move", input -> {
            String name = getName(input);
            if (!catalogCollection.move(name, catalogCollection.getCurrentCatalog())) {
                sayDirectoryNotFound();
            }
        });

        mapCommands.put("back", input -> {
            if (!catalogCollection.back()) {
                System.out.println("you are in the root directory");
            }
        });

        mapCommands.put("search", input -> {
            String choose = selectMedia(input);
            switch (choose) {
                case "1":
                    Video video = new Video();
                    searchMedia(input, video);
                    break;
                case "2":
                    Audio audio = new Audio();
                    searchMedia(input, audio);
                    break;
                case "3":
                    Book book = new Book();
                    searchMedia(input, book);
                    break;
                case "4":
                    Image image = new Image();
                    searchMedia(input, image);
                    break;
                default:
                    System.out.println("wrong input format (example: 1");
                    break;
            }
        });

        mapCommands.put("save", input -> {
            boolean a = save(catalogCollection, TEMP_OUT_CATALOGS);
            boolean b = save(collection, TEMP_OUT_MEDIA);
            if (a && b) {
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

    public static List<Field> getInheritedPrivateFields(Class<?> type) {
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
        List<Field> inheritedPrivateFields = getInheritedPrivateFields(mediaClass);
        for (int i = inheritedPrivateFields.size()-1; i >= 0; i--) {
            Class a = inheritedPrivateFields.get(i).getType();
            MediaInfo mediaInfo = inheritedPrivateFields.get(i).getAnnotation(MediaInfo.class);
            if (mediaInfo == null) {
                continue;
            }
            System.out.print(mediaInfo.name());
            String nextLine = input.nextLine();
            inheritedPrivateFields.get(i).setAccessible(true);
            try {
                if (a == Integer.class) {
                    Integer integer = new Integer(nextLine);
                    inheritedPrivateFields.get(i).set(media, integer);
                } else {
                    inheritedPrivateFields.get(i).set(media, nextLine);
                }
            } catch (IllegalAccessException e) {
                System.out.println("error");
            } catch (NumberFormatException e) {
                System.out.println("wrong input format");
                i++;
            }
        }
    }

    private static void searchMedia(Scanner input, MediaResource mediaResource) {
        String answer = selectStrict(input);
        System.out.println("if you do not want to search at the current attribute, press \"enter\"");
        setObject(input, mediaResource);
        switch (answer) {
            case "1":
                if (collection.search(mediaResource, true)) {
                    sayDone();
                    return;
                }
                System.out.println("elements for this search are not found");
                break;
            case "2":
                if (collection.search(mediaResource, false)) {
                    sayDone();
                    return;
                }
                System.out.println("elements for this search are not found");
                break;
            default:
                System.out.println("incorrect input");
                break;
        }
    }

    private static void addMedia(Scanner input, MediaResource mediaResource) {
        setObject(input, mediaResource);
        mediaResource.setExternalCatalog(catalogCollection.getCurrentCatalog());
        if (collection.add(mediaResource)) {
            sayDone();
            return;
        }
        System.out.println("media resource with that name already exists");
    }

    private static String selectMedia(Scanner input) {
        System.out.println("select number:");
        System.out.println("1.Video");
        System.out.println("2.Audio");
        System.out.println("3.Book");
        System.out.println("4.Image");

        return input.nextLine();
    }

    private static String selectStrict(Scanner input) {
        System.out.println("you want to use strict search?");
        System.out.println("1.Yes");
        System.out.println("2.No");

        return  input.nextLine();
    }

    private static String getName(Scanner input) {
        System.out.print("name: ");
        return input.nextLine();
    }

    private static String getNewName(Scanner input) {
        System.out.print("new name: ");
        return input.nextLine();
    }

    private static void sayMediaNotFound() {
        System.out.println("media with that name is not found");
    }

    private static void sayDirectoryNotFound() {
        System.out.println("directory with that name is not found");
    }

    private static void sayDone() {
        System.out.println("done");
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
