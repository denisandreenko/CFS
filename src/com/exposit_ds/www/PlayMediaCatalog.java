package com.exposit_ds.www;

import com.exposit_ds.www.catalogDescription.CatalogCollection;
import com.exposit_ds.www.mediaDescription.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayMediaCatalog {
    
    public static final String TEMP_OUT = "temp.out";
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
            }
        }
    }

    public static void command() throws FileNotFoundException {

        mapCommands.put("help", input -> help());

        mapCommands.put("add -m", input -> {
            System.out.println("select number, what you want to add:");
            System.out.println("1.Video");
            System.out.println("2.Audio");
            System.out.println("3.Book");
            System.out.println("4.Image");

            String choose = input.nextLine();

            switch (choose) {
                case "1":
                    addVideo(input);
                    break;
                case "2":
                    addAudio(input);
                    break;
                case "3":
                    addBook(input);
                    break;
                case "4":
                    addImage(input);
                    break;
                default:
                    System.out.println("incorrect input, repeat attempt");
                    break;
            }
        });

        mapCommands.put("edit -m", input -> {
            String name = getName(input);
            MediaResource media = collection.findForEdit(name);
            switch (media.getType()) {
                case VIDEO:
                    editVideo(input, (Video) media);
                    break;
                case AUDIO:
                    editAudio(input, (Audio) media);
                    break;
                case BOOK:
                    editBook(input, (Book) media);
                    break;
                case IMAGE:
                    editImage(input, (Image) media);
                    break;
                default:
                    System.out.println("media not found");
                    break;
            }
        });

        mapCommands.put("delete -m", input -> {
            String name = getName(input);
            collection.delete(name);
        });

        mapCommands.put("add -f", input -> {
            String name = getName(input);
            collection.addFavorites(name);
        });

        mapCommands.put("show -f", input -> collection.showFavorites());

        mapCommands.put("delete -f", input -> {
            String name = getName(input);
            collection.deleteFavorites(name);
        });

        mapCommands.put("add -c", input -> {
            String name = getName(input);
            catalogCollection.add(name, catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("edit -c", input -> {
            String name = getName(input);
            String newName = getNewName(input);
            catalogCollection.edit(name, newName);
        });

        mapCommands.put("show", input -> {
            catalogCollection.show(catalogCollection.getCurrentCatalog());
            collection.show(catalogCollection.getCurrentCatalog());
        });

        mapCommands.put("delete -c", input -> {
            String name = getName(input);
            catalogCollection.delete(name);
        });

        mapCommands.put("move", input -> {
            String name = getName(input);
            catalogCollection.move(name);
        });

        mapCommands.put("back", input -> catalogCollection.back());

        mapCommands.put("save", input -> {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(TEMP_OUT));
                objectOutputStream.writeObject(collection);
                objectOutputStream.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, "exception: ", ex);
            }
        });

        mapCommands.put("read", input -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("temp.out"));
                String a = bufferedReader.readLine();
                if (a != null) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("temp.out"));

                    collection = (MediaCollection<MediaResource>) objectInputStream.readObject();

                    objectInputStream.close();
                } else {
                    System.out.println("File is empty");
                }
                bufferedReader.close();
            } catch (IOException | ClassNotFoundException ex) {
                log.log(Level.SEVERE, "exception: ", ex);
            }
        });

        mapCommands.put("exit", input -> System.exit(0));
    }

    private static String getName(Scanner input) {
        System.out.println("enter name");
        return input.nextLine();
    }

    private static void editImage(Scanner input, Image media) {
        String nameImage = getNewName(input);
        media.setName(nameImage);
    }

    private static void editBook(Scanner input, Book media) {
        String nameBook = getNewName(input);
        int yearBook = getYear(input);
        String nameAuthor = getAuthor(input);
        media.setName(nameBook);
        media.setYear(yearBook);
        media.setNameAuthor(nameAuthor);
    }

    private static String getNewName(Scanner input) {
        System.out.println("enter new name");
        return input.nextLine();
    }

    private static void editAudio(Scanner input, Audio media) {
        String nameAudio = getNewName(input);
        String nameSinger = getSinger(input);
        media.setName(nameAudio);
        media.setNameSinger(nameSinger);
    }

    private static void editVideo(Scanner input, Video media) {
        String nameVideo = getNewName(input);
        int yearVideo = getYear(input);
        media.setName(nameVideo);
        media.setYear(yearVideo);
    }

    private static int getYear(Scanner input) {
        System.out.println("enter year");
        return Integer.parseInt(input.nextLine());
    }

    private static String getAuthor(Scanner input) {
        System.out.println("enter author");
        return input.nextLine();
    }

    private static String getSinger(Scanner input) {
        System.out.println("enter singer");
        return input.nextLine();
    }

    private static void addAudio(Scanner input) {
        String nameAudio = getName(input);
        String nameSinger = getSinger(input);
        collection.add(new Audio(nameAudio, catalogCollection.getCurrentCatalog(), nameSinger));
    }

    private static void addVideo(Scanner input) {
        String nameVideo = getName(input);
        int yearVideo = getYear(input);
        collection.add(new Video(nameVideo, catalogCollection.getCurrentCatalog(), yearVideo));
    }

    private static void addBook(Scanner input) {
        String nameBook = getName(input);
        int yearBook = getYear(input);
        String nameAuthor = getAuthor(input);
        collection.add(new Book(nameBook, catalogCollection.getCurrentCatalog(), yearBook, nameAuthor));
    }

    private static void addImage(Scanner input) {
        String nameImage = getName(input);
        collection.add(new Image(nameImage, catalogCollection.getCurrentCatalog()));
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
        System.out.println("save - save media catalog data");
        System.out.println("read - read media catalog data");
        System.out.println("exit - exit application");
    }
}
