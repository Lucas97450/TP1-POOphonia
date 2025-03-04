package models;

public class Album extends MusicItem {
    private String artist;
    private int numberOfTracks;
    private String label;

<<<<<<< HEAD
    public Album(int id, String title, int releaseYear, String artist, String label, int numberOfTracks) {
        super(id, title, releaseYear);
        if (numberOfTracks < 1 || numberOfTracks > 100) {
            throw new IllegalArgumentException("Nombre de pistes invalide");
        }
=======
    /**
     * Constructeur principal avec des paramètres individuels.
     */
    public Album(int id, String title, int releaseYear, String artist, int numberOfTracks, String label) {
        super(id, title, releaseYear);
        if (numberOfTracks < 1 || numberOfTracks > 100)
            throw new IllegalArgumentException("Invalid number of tracks: " + numberOfTracks);

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
        this.label = label;
    }

<<<<<<< HEAD
    public String getArtist() {
        return artist;
    }

    @Override
    public String getInfo() {
        return "Album: " + title + " by " + artist + " (" + numberOfTracks + " tracks, " + label + ")";
=======
    /**
     * Constructeur qui prend directement un tableau `parts` (utilisé par `MusicItemFactory`).
     */
    public Album(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.artist = parts[4];
        this.label = parts[5]; // ⚠️ Vérifier l'ordre avec MusicItemFactory
        this.numberOfTracks = Integer.parseInt(parts[6]);

        if (numberOfTracks < 1 || numberOfTracks > 100)
            throw new IllegalArgumentException("Invalid number of tracks: " + numberOfTracks);
    }



    // Getters et Setters
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public int getNumberOfTracks() { return numberOfTracks; }
    public void setNumberOfTracks(int numberOfTracks) {
        if (numberOfTracks < 1 || numberOfTracks > 100)
            throw new IllegalArgumentException("Invalid number of tracks: " + numberOfTracks);
        this.numberOfTracks = numberOfTracks;
    }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String getInfo() {
        return "Album [ID=" + getId() + ", Title=" + getTitle() + ", Year=" + getReleaseYear() +
                ", Artist=" + artist + ", Tracks=" + numberOfTracks + ", Label=" + label + "]";
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }

    @Override
    public String toCSV() {
<<<<<<< HEAD
        return "album," + id + "," + title + "," + releaseYear + "," + artist + "," + label + "," + numberOfTracks;
=======
        return "album," + getId() + "," + getTitle() + "," + getReleaseYear() + "," + artist + "," + numberOfTracks + "," + label;
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }
}
