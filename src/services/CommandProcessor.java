package services;

<<<<<<< HEAD
import models.MusicItem;
import models.MusicItemFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Gère l'exécution des commandes pour la MusicLibrary
 * (sans switch-case ni if/else multiples).
 */
public class CommandProcessor {

    private final MusicLibrary library;
    private static final Map<String, Command> commands = new HashMap<>();

    public CommandProcessor(MusicLibrary library) {
        this.library = library;
        initCommands();
    }

    public static void processCommands(String s) {
    }

    /**
     * Initialise la map de commandes (pattern Command).
     */
    private void initCommands() {
        commands.put("ADD", new AddCommand());
        commands.put("REMOVE", new RemoveCommand());
        commands.put("PLAY", new PlayCommand());
        commands.put("PAUSE", new PauseCommand());
        commands.put("STOP", new StopCommand());
        commands.put("LIST", new ListCommand());
        commands.put("CLEAR", new ClearCommand());
        commands.put("SOURCE", new SourceCommand());
        commands.put("EXIT", new ExitCommand());
        commands.put("SEARCH", new SearchCommand());
        commands.put("SAVE", new SaveCommand());
        commands.put("LOAD", new LoadCommand());
    }

    /**
     * Lit un fichier de commandes (ex. commands.txt) et exécute ligne par ligne.
     */
    public void processCommandFile(String filename) {
        File file = new File("data/" + filename);
        if (!file.exists()) {
            System.out.println("Fichier non trouvé: " + filename);
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                processCommand(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier: " + filename);
        }
    }

    /**
     * Traite une ligne de commande unique.
     */
    public static void processCommand(String line) {
        // Ignorer commentaires (#...) et lignes vides
        if (line.isEmpty() || line.startsWith("#")) {
            return;
        }

        // Séparer la commande (ex. "ADD") de ses arguments
        String[] parts = line.split(" ", 2);
        String action = parts[0].toUpperCase();              // Nom de la commande
        String arguments = (parts.length > 1) ? parts[1] : ""; // Le reste de la ligne

        // Cherche la commande dans la map
        Command command = commands.get(action);
        if (command != null) {
            command.execute(arguments);
        } else {
            System.out.println("Commande inconnue: " + action);
        }
    }

    /**
     * Quitte le programme.
     */
    private void exitProgram() {
        System.out.println("Sortie du programme...");
        System.exit(0);
    }

    /**
     * Convertit un argument en ID entier, ou renvoie -1 en cas d'erreur.
     */
    private int parseId(String args) {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: ID invalide (" + args + ")");
            return -1;
        }
    }

    /**
     * Interface Command: chaque commande implémente la méthode execute(args).
     */
    private interface Command {
        void execute(String args);
    }

    // ------------------------------------------------------------------------
    // Implémentations de chaque commande sous forme de classes internes privées
    // ------------------------------------------------------------------------

    private class AddCommand implements Command {
        @Override
        public void execute(String args) {
            MusicItem item = MusicItemFactory.createMusicItem(args);
            if (item != null) {
                library.addItem(item);
            } else {
                System.out.println("Erreur: Format ADD invalide.");
            }
        }
    }

    private class RemoveCommand implements Command {
        @Override
        public void execute(String args) {
            int id = parseId(args);
            if (id != -1) {
                library.removeItem(id);
            }
        }
    }

    private class PlayCommand implements Command {
        @Override
        public void execute(String args) {
            int id = parseId(args);
            if (id != -1) {
                library.playItem(id);
            }
        }
    }

    private class PauseCommand implements Command {
        @Override
        public void execute(String args) {
            library.pauseItem();
        }
    }

    private class StopCommand implements Command {
        @Override
        public void execute(String args) {
            library.stopItem();
        }
    }

    private class ListCommand implements Command {
        @Override
        public void execute(String args) {
            library.listAllItems();
        }
    }

    private class ClearCommand implements Command {
        @Override
        public void execute(String args) {
            library.clearAllItems();
        }
    }

    private class SourceCommand implements Command {
        @Override
        public void execute(String args) {
            // Si l'argument est vide, on lit par défaut "commands.txt"
            String fileToRead = args.isEmpty() ? "commands.txt" : args;
            processCommandFile(fileToRead);
        }
    }

    private class ExitCommand implements Command {
        @Override
        public void execute(String args) {
            exitProgram();
        }
    }

    private class SearchCommand implements Command {
        @Override
        public void execute(String args) {
            library.searchItem(args);
        }
    }

    private class SaveCommand implements Command {
        @Override
        public void execute(String args) {
            library.saveToCSV(args.isEmpty() ? "data/POOphonia.csv" : args);
        }
    }

    private class LoadCommand implements Command {
        @Override
        public void execute(String args) {
            library.loadFromCSV(args.isEmpty() ? "data/POOphonia.csv" : args);
        }
    }

}


=======
import services.MusicLibrary;
import models.MusicItemFactory;
import models.MusicItem;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CommandProcessor {
    private static final String DEFAULT_COMMAND_FILE = "data/commands.txt";
    private static Set<String> sourcedFiles = new HashSet<>(); // Évite les boucles infinies

    /**
     * Méthode statique pour exécuter les commandes en utilisant MusicLibrary.
     * @param library Instance de MusicLibrary.
     */
    public static void processCommands(MusicLibrary library) {
        processCommands(library, ""); // Appelle la version avec le fichier par défaut
    }

    /**
     * Lit et exécute un fichier de commandes.
     * @param library La bibliothèque musicale associée.
     * @param filename Nom du fichier de commandes, ou "" pour `commands.txt`.
     */
    public static void processCommands(MusicLibrary library, String filename) {
        if (filename == null || filename.isEmpty()) {
            filename = DEFAULT_COMMAND_FILE; // Utiliser `commands.txt` si aucun fichier n'est précisé
        }

        if (sourcedFiles.contains(filename)) {
            System.out.println("Currently sourcing " + filename + "; SOURCE ignored.");
            return;
        }

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Sourcing " + filename + " failed; file not found.");
            return;
        }

        System.out.println("Sourcing " + filename + "...");

        sourcedFiles.add(filename); // Marquer comme en cours de traitement

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                executeCommand(library, line);
            }
        } catch (IOException e) {
            System.out.println("Error reading command file: " + filename);
        }

        sourcedFiles.remove(filename); // Retirer après exécution
    }

    /**
     * Exécute une commande spécifique.
     * @param library Instance de MusicLibrary.
     * @param command Ligne de commande.
     */
    private static void executeCommand(MusicLibrary library, String command) {
        String[] parts = command.split(" ", 2);
        String action = parts[0].toUpperCase();

        try {
            switch (action) {
                case "SOURCE":
                    String fileToSource = parts.length > 1 ? parts[1] : "";
                    processCommands(library, fileToSource);
                    break;
                case "ADD":
                    library.addItem(MusicItemFactory.createFromCSV(parts[1].split(",")));
                    break;
                case "REMOVE":
                    library.removeItem(Integer.parseInt(parts[1]));
                    break;
                case "LIST":
                    library.listAllItems();
                    break;
                case "SEARCH":
                    if (parts.length < 2) {
                        System.out.println("Invalid SEARCH command: " + command);
                        break;
                    }
                    // Rechercher un élément (par ID ou "title by artist")
                    library.searchItem(parts[1]);
                    break;
                case "PLAY":
                    if (parts.length < 2) {
                        System.out.println("Invalid PLAY command: " + command);
                        break;
                    }
                    library.playItem(String.valueOf(Integer.parseInt(parts[1]))); // ⚠️ Vérifier que playItem existe bien
                    break;
                case "LOAD":
                    library.loadLibrary(parts[1]);
                    break;
                case "SAVE":
                    library.save(""); // ⚠️ Vérifier que `save("")` fonctionne bien dans MusicLibrary
                    break;
                case "EXIT":
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
        } catch (Exception e) {
            System.out.println("Invalid command: " + command);
        }
    }
}

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
