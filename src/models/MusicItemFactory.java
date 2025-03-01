package models;

public class MusicItemFactory {
    public static MusicItem createMusicItem(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 2) {
            System.out.println("Format CSV invalide.");
            return null;
        }

        String type = parts[0].toLowerCase();

        try {
            int id = Integer.parseInt(parts[1]);
            String title = parts[2];
            int releaseYear = Integer.parseInt(parts[3]);

            if (type.equals("song")) {
                return new Song(id, title, releaseYear, parts[4], parts[5], Integer.parseInt(parts[6]));
            } else if (type.equals("album")) {
                return new Album(id, title, releaseYear, parts[4], parts[5], Integer.parseInt(parts[6]));
            } else if (type.equals("podcast")) {
                return new Podcast(id, title, releaseYear, parts[4], parts[5], Integer.parseInt(parts[6]));
            } else {
                System.out.println("Type inconnu: " + type);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de l'élément: " + e.getMessage());
            return null;
        }
    }
}
