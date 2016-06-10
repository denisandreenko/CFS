package com.exposit_ds.www;

import com.exposit_ds.www.mediaDescription.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayMediaCatalog {
    private static Map<String, CommandManager> mapCommands = new HashMap<>();
    public static AbstractCollection<MediaResourse> collection = new AbstractCollection<>();
    private static Logger log = Logger.getLogger(PlayMediaCatalog.class.getName());

    public static void playMediaCatalog() throws FileNotFoundException{
        System.out.println("Enter 'help' to see a list of commands");
        while (true){
            command();
            Scanner input = new Scanner(System.in);

            String key = input.nextLine();

            if (mapCommands.containsKey(key)) mapCommands.get(key).runCommand();

        }
    }

    public static void command() throws FileNotFoundException{

        mapCommands.put("help", new CommandManager() {
            @Override
            public void runCommand() {
                help();
            }
        });

        mapCommands.put("show", new CommandManager() {
            @Override
            public void runCommand() {
                collection.show();
            }
        });

        mapCommands.put("add", new CommandManager() {
            @Override
            public void runCommand() {
                Scanner input = new Scanner(System.in);

                System.out.println("Select number, what you want to add:");
                System.out.println("1.Video");
                System.out.println("2.Audio");
                System.out.println("3.Book");
                System.out.println("4.Image");
                String choose = input.nextLine();

                switch (choose) {
                    case "1":
                        System.out.println("Enter name");
                        String nameVideo = input.nextLine();

                        System.out.println("Enter year");
                        int yearVideo = Integer.parseInt(input.nextLine());

                        collection.add(new Video(nameVideo, yearVideo));

                        MediaResourse mediaResourse = new Video(nameVideo, yearVideo);
                        Video video = (Video) mediaResourse;
                        break;
                    case "2":
                        System.out.println("Enter name");
                        String nameAudio = input.nextLine();

                        System.out.println("Enter name singer");
                        String nameSinger = input.nextLine();

                        collection.add(new Audio(nameAudio, nameSinger));
                        break;
                    case "3":
                        System.out.println("Enter name");
                        String nameBook = input.nextLine();

                        System.out.println("Enter year");
                        int yearBook = Integer.parseInt(input.nextLine());

                        System.out.println("Enter name author");
                        String nameAuthor = input.nextLine();

                        collection.add(new Books(nameBook, yearBook, nameAuthor));
                        break;
                    case "4":
                        System.out.println("Enter name");
                        String nameImage = input.nextLine();

                        collection.add(new Images(nameImage));
                        break;
                    default:
                        System.out.println("Incorrect input, repeat attempt");
                        break;
                }
            }
        });

        mapCommands.put("delete", new CommandManager() {
            @Override
            public void runCommand() {
                Scanner input = new Scanner(System.in);

                System.out.println("Enter name");
                String name = input.nextLine();

                collection.delete(name);
            }
        });

        mapCommands.put("save", new CommandManager() {
            @Override
            public void runCommand() {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("temp.out"));
                    objectOutputStream.writeObject(collection);
                    objectOutputStream.close();
                }
                catch (IOException ex)
                {
                    log.log(Level.SEVERE, "Exception: ", ex);
                }
            }
        });

        mapCommands.put("read", new CommandManager() {
            @Override
            public void runCommand() {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("temp.out"));
                    String a = bufferedReader.readLine();
                    if (a != null) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("temp.out"));

                        collection = (AbstractCollection<MediaResourse>) objectInputStream.readObject();

                        objectInputStream.close();
                    } else {
                        System.out.println("File is empty");
                    }
                    bufferedReader.close();
                }
                catch (IOException ex)
                {
                    log.log(Level.SEVERE, "Exception: ", ex);
                }
                catch (ClassNotFoundException ex)
                {
                    log.log(Level.SEVERE, "Exception: ", ex);
                }
            }
        });

        mapCommands.put("exit", new CommandManager() {
            @Override
            public void runCommand() {
                return;
            }
        });
    }

    public static void help() {
        System.out.println("*show - asdasdasd*");
        System.out.println("*add*");
        System.out.println("*delete");
        System.out.println("*read*");
        System.out.println("*save*");
        System.out.println("*exit*");
    }
}
