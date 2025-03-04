package services;

import models.MusicItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {
    private List<MusicItem> items;
    private MusicItem currentlyPlaying;

    public MusicLibrary() {
        this.items = new ArrayList<>();
        this.currentlyPlaying = null;
    }

    public void addItem(MusicItem item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public boolean removeItem(int id) {
        return items.removeIf(item -> item.getId() == id);
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("Music library is empty.");
        } else {
            for (MusicItem item : items) {
                System.out.println(item.toString());
            }
        }
    }

    public MusicItem searchItem(String searchInput) {
        try {
            int id = Integer.parseInt(searchInput);
            for (MusicItem item : items) {
                if (item.getId() == id) {
                    System.out.println(item.toString());
                    return item;
                }
            }
        } catch (NumberFormatException e) {
            String[] parts = searchInput.split(" by ");
            if (parts.length == 2) {
                String title = parts[0].trim();
                String artistOrHost = parts[1].trim();
                for (MusicItem item : items) {
                    if (item.getTitle().equalsIgnoreCase(title)) {
                        if ((item instanceof models.Song && ((models.Song) item).getArtist().equalsIgnoreCase(artistOrHost)) ||
                                (item instanceof models.Album && ((models.Album) item).getArtist().equalsIgnoreCase(artistOrHost)) ||
                                (item instanceof models.Podcast && ((models.Podcast) item).getHost().equalsIgnoreCase(artistOrHost))) {
                            System.out.println(item.toString());
                            return item;
                        }
                    }
                }
            }
        }
        System.out.println("SEARCH " + searchInput + " failed; no item found.");
        return null;
    }

    public void playItem(String searchInput) {
        MusicItem item = searchItem(searchInput);
        if (item != null) {
            stopPlaying();
            item.play();
            currentlyPlaying = item;
            System.out.println("Playing " + item.toString());
        }
    }

    public void stopPlaying() {
        if (currentlyPlaying != null) {
            currentlyPlaying.stop();
            currentlyPlaying = null;
        }
    }

    public void pauseItem() {
        if (currentlyPlaying != null) {
            currentlyPlaying.pause();
            System.out.println("Pausing " + currentlyPlaying.toString());
        } else {
            System.out.println("No item to PAUSE.");
        }
    }

    public void clearLibrary() {
        items.clear();
        currentlyPlaying = null;
        System.out.println("Music library has been cleared successfully.");
    }

    public void save(String fileName) {
        if (fileName.isEmpty()) {
            fileName = "data/POOphonia.csv"; // Fichier par défaut
        } else {
            fileName = "data/" + fileName + ".csv";
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (MusicItem item : items) {
                writer.println(item.toCSV());
            }
            System.out.println("Library saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving library: " + fileName);
        }
    }

    public void loadLibrary(String filename) {
        if (filename.isEmpty()) {
            filename = "POOphonia"; // Fichier par défaut
        }
        this.items = MusicLibraryFileHandler.loadLibrary(filename);
    }


}
