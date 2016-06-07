package com.exposit_ds.www;

import com.exposit_ds.www.collections.AbstractCollection;
import com.exposit_ds.www.desctiptionMedia.MediaResourse;

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

    public static void command() throws FileNotFoundException{

        mapCommands.put("Add", new CommandManager() {
            @Override
            public void runCommand() {
                Scanner input = new Scanner(System.in);

                System.out.println("Enter name");
                String name = input.nextLine();

                System.out.println("Enter year");
                String year = input.nextLine();

                System.out.println("Enter main author name");
                String nameAuthor = input.nextLine();

                collection.add(new MediaResourse(name, year, nameAuthor));
            }
        });

        mapCommands.put("Save", new CommandManager() {
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

        mapCommands.put("Read", new CommandManager() {
            @Override
            public void runCommand() {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("temp.out"));

                    if (bufferedReader.readLine() != null) {

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

        mapCommands.put("Exit", new CommandManager() {
            @Override
            public void runCommand() {
                return;
            }
        });
    }

    public static void playMediaCatalog(){
        while (true){
            help();

            Scanner input = new Scanner(System.in);

            String key = input.nextLine();

            if (mapCommands.containsKey(key)) mapCommands.get(key).runCommand();
        }
    }

    public static void help() {
        System.out.println("_________________________________");
        System.out.println("*Show*");
        System.out.println("*Add*");
        System.out.println("*Exit*");
        System.out.println("*Read*");
        System.out.println("*Save*");
        System.out.println("_________________________________");
    }
}
