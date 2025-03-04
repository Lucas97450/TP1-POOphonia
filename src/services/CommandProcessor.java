package services;

import models.MusicItem;
import models.MusicItemFactory;
import services.MusicLibrary;
import java.io.*;

public class CommandProcessor {
    private static final String DEFAULT_COMMAND_FILE = "data/commands.txt";

    /**
     * Exécute les commandes depuis le fichier par défaut.
     * @param library Instance de MusicLibrary.
     */
    public static void processCommands(MusicLibrary library) {
        processCommands(library, DEFAULT_COMMAND_FILE);
    }

    /**
     * Lit et exécute un fichier de commandes.
     * @param library Instance de MusicLibrary.
     * @param filename Nom du fichier de commandes.
     */
    public static void processCommands(MusicLibrary library, String filename) {
        File commandFile = new File(filename);
        if (!commandFile.exists()) {
            System.out.println("Sourcing " + filename + " failed; file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(commandFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                executeCommand(library, line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading command file: " + filename);
        }
    }

    /**
     * Exécute une commande spécifique.
     * @param library Instance de MusicLibrary.
     * @param command Ligne de commande.
     */
    private static void executeCommand(MusicLibrary library, String command) {
        if (command.isEmpty() || command.startsWith("#")) return; // Ignore les commentaires et les lignes vides

        String[] parts = command.split(" ", 2);
        String action = parts[0].trim().toUpperCase();
        String argument = (parts.length > 1) ? parts[1].trim() : "";

        switch (action) {
            case "ADD":
                addItem(library, argument);
                break;

            case "REMOVE":
                removeItem(library, argument);
                break;

            case "SEARCH":
                library.searchItem(argument);
                break;

            case "PLAY":
                library.playItem(argument);
                break;

            case "PAUSE":
                library.pauseItem();
                break;

            case "STOP":
                library.stopPlaying();
                break;

            case "LIST":
                library.listItems();
                break;

            case "CLEAR":
                library.clearLibrary();
                break;

            case "LOAD":
                if (argument.isEmpty()) {
                    System.out.println("Invalid LOAD command.");
                } else {
                    library.loadLibrary(argument);
                }
                break;

            case "SAVE":
                library.save(argument.isEmpty() ? "" : argument);
                break;

            case "EXIT":
                System.out.println("Exiting program...");
                System.exit(0);
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }

    /**
     * Ajoute un élément à la bibliothèque musicale.
     * @param library Instance de MusicLibrary.
     * @param data Données CSV de l'élément à ajouter.
     */
    private static void addItem(MusicLibrary library, String data) {
        String[] parts = data.split(",");
        if (parts.length != 7) {
            System.out.println("Wrong number of elements: " + data);
            return;
        }

        MusicItem item = MusicItemFactory.createFromCSV(parts);
        if (item != null) {
            library.addItem(item);
            System.out.println(item.toString() + " added to the library successfully.");
        } else {
            System.out.println("Invalid music item: " + data);
        }
    }

    /**
     * Supprime un élément de la bibliothèque musicale.
     * @param library Instance de MusicLibrary.
     * @param idStr ID de l'élément à supprimer.
     */
    private static void removeItem(MusicLibrary library, String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            if (library.removeItem(id)) {
                System.out.println("Removed item " + id + " successfully.");
            } else {
                System.out.println("REMOVE item " + id + " failed; no such item.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID for REMOVE command: " + idStr);
        }
    }
}
