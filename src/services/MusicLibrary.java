package services;

<<<<<<< HEAD
import models.MusicItem;
import models.MusicItemFactory;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class MusicLibrary {
    private List<MusicItem> items = new ArrayList<>();
    private MusicItem currentlyPlaying; // Référence à l'élément en cours de lecture

    public MusicLibrary() {
        this.items = new ArrayList<>();
        this.currentlyPlaying = null;
    }

    // ✅ Ajouter un élément musical
    public void addItem(MusicItem item) {
        if (item != null) {
            items.add(item);
            System.out.println("Ajouté : " + item.getInfo());
        } else {
            System.out.println("Erreur : Élément musical invalide.");
        }
    }

    // ✅ Supprimer un élément par ID
    public void removeItem(int id) {
        MusicItem toRemove = findItemById(id);
        if (toRemove != null) {
            items.remove(toRemove);
            System.out.println("Supprimé : " + toRemove.getInfo());
        } else {
            System.out.println("Erreur : Aucun élément trouvé avec l'ID " + id);
        }
    }

    // ✅ Lister tous les éléments de la bibliothèque
    public void listAllItems() {
        if (items.isEmpty()) {
            System.out.println("La bibliothèque est vide.");
        } else {
            System.out.println("Contenu de la bibliothèque :");
            for (MusicItem item : items) {
                System.out.println(item.getInfo());
            }
        }
    }

    // ✅ Trouver un élément par ID
    private MusicItem findItemById(int id) {
        for (MusicItem item : items) {
            if (item != null && item.getId() == id) {
=======
import models.*;

import java.util.ArrayList;
import java.io.*;

public class MusicLibrary {
    private ArrayList<MusicItem> items;
    private static final String DEFAULT_FILE = "data/POOphonia.csv";
    private MusicItem currentlyPlaying = null;

    public MusicLibrary() {
        this.items = new ArrayList<>();
    }

    public void addItem(MusicItem item) {
        if (getItemById(item.getId()) != null) {
            System.out.println("ADD " + item.getTitle() + " failed; ID already used.");
            return;
        }
        items.add(item);
        System.out.println(item.getTitle() + " added to the library successfully.");
        save(DEFAULT_FILE);
    }

    public void removeItem(int id) {
        MusicItem item = getItemById(id);
        if (item == null) {
            System.out.println("REMOVE item " + id + " failed; no such item.");
            return;
        }
        items.remove(item);
        System.out.println("Removed " + item.getTitle() + " successfully.");
        save(DEFAULT_FILE);
    }

    public void listAllItems() {
        if (items.isEmpty()) {
            System.out.println("Music library is empty.");
            return;
        }
        System.out.println("Library:");
        for (MusicItem item : items) {
            System.out.println(item);
        }
    }

    public void searchItem(String searchInput) {
        // Vérifier si c'est un ID
        int id = parseIntOrDefault(searchInput, -1);
        if (id != -1) {
            for (MusicItem item : items) {
                if (item.getId() == id) {
                    System.out.println(formatItem(item)); // ✅ Afficher seulement "title by artist/host"
                    return;
                }
            }
        }

        // Vérifier si c'est une recherche "title by artist"
        if (searchInput.contains(" by ")) {
            String[] parts = searchInput.split(" by ");
            if (parts.length == 2) {
                String title = parts[0].trim();
                String artist = parts[1].trim();
                for (MusicItem item : items) {
                    if (item instanceof Song) {
                        Song song = (Song) item;
                        if (song.getTitle().equalsIgnoreCase(title) &&
                                song.getArtist().equalsIgnoreCase(artist)) {
                            System.out.println(formatItem(song));
                            return;
                        }
                    } else if (item instanceof Album) {
                        Album album = (Album) item;
                        if (album.getTitle().equalsIgnoreCase(title) &&
                                album.getArtist().equalsIgnoreCase(artist)) {
                            System.out.println(formatItem(album));
                            return;
                        }
                    } else if (item instanceof Podcast) {
                        Podcast podcast = (Podcast) item;
                        if (podcast.getTitle().equalsIgnoreCase(title) &&
                                podcast.getHost().equalsIgnoreCase(artist)) { // Pour podcast, on vérifie le host
                            System.out.println(formatItem(podcast));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("SEARCH " + searchInput + " failed; no item found.");
    }

    // Méthode pour formater l'affichage
    private String formatItem(MusicItem item) {
        if (item instanceof Song) {
            Song song = (Song) item;
            return song.getTitle() + " by " + song.getArtist();
        } else if (item instanceof Album) {
            Album album = (Album) item;
            return album.getTitle() + " by " + album.getArtist();
        } else if (item instanceof Podcast) {
            Podcast podcast = (Podcast) item;
            return podcast.getTitle() + " by " + podcast.getHost(); // Host au lieu de artist
        }
        return "Unknown item";
    }





    public void playItem(String query) {
        MusicItem item = null;

        // Vérifier si l'entrée est un ID (nombre)
        int id = parseIntOrDefault(query, -1);
        if (id != -1) {
            item = getItemById(id);
        } else {
            searchItem(query); // Recherche par "title by artist"
        }

        // Vérifier si l'élément existe
        if (item == null) {
            System.out.println("PLAY " + query + " failed; no such item.");
            return;
        }

        stopPlaying(); // Arrêter l'élément en cours
        currentlyPlaying = item; // Mettre à jour l'élément joué
        System.out.println("Playing " + item.getInfo());
    }

    // Convertir une chaîne en entier avec une valeur par défaut
    private int parseIntOrDefault(String s, int defaultValue) {
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return defaultValue; }
    }


    public void clearLibrary() {
        items.clear();
        System.out.println("Music library has been cleared successfully.");
    }

    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (MusicItem item : items) {
                writer.println(item.toCSV());
            }
            System.out.println("Library saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving library: " + filename);
        }
    }

    public void loadLibrary(String filename) {
        items.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                MusicItem item = MusicItemFactory.createFromCSV(line.split(","));
                if (item != null) {
                    items.add(item);
                }
            }
            System.out.println("Library in file " + filename + " loaded successfully.");
        } catch (IOException e) {
            System.out.println("No library found; starting fresh.");
        }
    }

    public void pauseItem() {
        for (MusicItem item : items) {
            if (item.isPlaying()) {
                item.pause();
                return;
            }
        }
        System.out.println("No item to PAUSE.");
    }

    public void stopItem() {
        for (MusicItem item : items) {
            if (item.isPlaying()) {
                item.stop();
                return;
            }
        }
        System.out.println("No item to STOP.");
    }

    public void clearAllItems() {
        if (items.isEmpty()) {
            System.out.println("Music library is already empty.");
            return;
        }
        items.clear();
        System.out.println("Music library has been cleared successfully.");
    }

    private MusicItem getItemById(int id) {
        for (MusicItem item : items) {
            if (item.getId() == id) {
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
                return item;
            }
        }
        return null;
    }

<<<<<<< HEAD
    // ✅ Jouer un élément musical par ID
    public void playItem(int id) {
        MusicItem item = findItemById(id);
        if (item != null) {
            if (currentlyPlaying != null) {
                currentlyPlaying.stop();
            }
            item.play();
            currentlyPlaying = item;
            System.out.println("Lecture de : " + item.getInfo());
        } else {
            System.out.println("Erreur : Aucun élément trouvé avec l'ID " + id);
        }
    }

    // ✅ Mettre en pause
    public void pauseItem() {
        if (currentlyPlaying != null) {
            System.out.println("Pause : " + currentlyPlaying.getInfo());
            currentlyPlaying.stop();
            currentlyPlaying = null;
        } else {
            System.out.println("Erreur : Aucun élément en cours de lecture.");
        }
    }

    // 🔹 Recherche un élément par titre ou artiste
    public void searchItem(String query) {
        if (query.isEmpty()) {
            System.out.println("Erreur: Veuillez entrer un mot-clé pour la recherche.");
            return;
        }

        List<MusicItem> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (MusicItem item : items) {
            if (item.getTitle().toLowerCase().contains(lowerQuery) ||
                    (item instanceof models.Song && ((models.Song) item).getArtist().toLowerCase().contains(lowerQuery)) ||
                    (item instanceof models.Album && ((models.Album) item).getArtist().toLowerCase().contains(lowerQuery)) ||
                    (item instanceof models.Podcast && ((models.Podcast) item).getHost().toLowerCase().contains(lowerQuery))) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            System.out.println("Aucun élément trouvé pour: " + query);
        } else {
            System.out.println("Résultats de la recherche pour: " + query);
            for (MusicItem item : results) {
                System.out.println(item.getInfo());
            }
        }
    }


    // ✅ Arrêter la lecture
    public void stopItem() {
        if (currentlyPlaying != null) {
            System.out.println("Arrêt de : " + currentlyPlaying.getInfo());
            currentlyPlaying.stop();
            currentlyPlaying = null;
        } else {
            System.out.println("Erreur : Aucun élément en cours de lecture.");
        }
    }

    // ✅ Vider la bibliothèque
    public void clearAllItems() {
        items.clear();
        currentlyPlaying = null;
        System.out.println("La bibliothèque a été vidée.");
    }

    public void saveToCSV(String filename) {
        if (filename.isEmpty()) {
            filename = "data/POOphonia.csv"; // Fichier par défaut
        }

        try (FileWriter writer = new FileWriter(filename)) {
            for (MusicItem item : items) {
                writer.write(item.toCSV() + "\n");
            }
            System.out.println("Bibliothèque sauvegardée dans : " + filename);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public void loadFromCSV(String filename) {
        if (filename.isEmpty()) {
            filename = "data/POOphonia.csv"; // Fichier par défaut
        }

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Fichier non trouvé : " + filename);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                MusicItem item = MusicItemFactory.createMusicItem(line);
                if (item != null) {
                    items.add(item);
                }
            }
            System.out.println("Bibliothèque chargée depuis : " + filename);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
=======
    private void stopPlaying() {
        for (MusicItem item : items) {
            if (item.isPlaying()) {
                item.stop();
            }
        }
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }
}


<<<<<<< HEAD


=======
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
