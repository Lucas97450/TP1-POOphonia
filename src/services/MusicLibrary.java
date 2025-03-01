package services;

import models.MusicItem;
import java.util.ArrayList;
import java.util.List;

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
                return item;
            }
        }
        return null;
    }

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
}
