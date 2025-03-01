package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandProcessor {

    // Liste pour suivre les fichiers déjà en train d'être "sourcés"
    private static final List<String> filesBeingSourced = new ArrayList<>();

    /**
     * Traite une ligne de commande, par exemple "SOURCE commands.txt".
     */
    public static void processCommands(String line) {
        // Ignorer lignes vides ou commentaires
        if (line == null || line.trim().isEmpty() || line.startsWith("#")) {
            return;
        }

        // Séparer la commande du reste (arguments)
        String[] parts = line.split(" ", 2);
        String command = parts[0].toUpperCase();       // ex. "SOURCE"
        String arguments = (parts.length > 1) ? parts[1] : ""; // ex. "commands.txt"

        switch (command) {
            case "SOURCE":
                sourceFile(arguments);
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Gère la commande SOURCE <filename>, qui lit un autre fichier de commandes.
     */
    private static void sourceFile(String filename) {
        // Si l'argument est vide, on prend un fichier par défaut
        if (filename.isEmpty()) {
            filename = "commands.txt";
        }

        // Empêcher la récursion infinie si on "SOURCE" le même fichier plusieurs fois
        if (filesBeingSourced.contains(filename)) {
            System.out.println("File " + filename + " is already being processed. Skipping to avoid loop.");
            return;
        }

        // On note qu'on est en train de sourcer ce fichier
        filesBeingSourced.add(filename);

        // On suppose que le fichier est dans un dossier "data"
        File file = new File("data/" + filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            filesBeingSourced.remove(filename);
            return;
        }

        System.out.println("Sourcing file: " + filename);

        // Lecture du fichier ligne par ligne
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                processCommands(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        } finally {
            // On a fini de sourcer, on retire le fichier de la liste
            filesBeingSourced.remove(filename);
            System.out.println("Done sourcing file: " + filename);
        }
    }
}
