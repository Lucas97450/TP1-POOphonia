package services;

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


