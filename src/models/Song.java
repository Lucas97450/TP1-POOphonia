package models;

public class Song extends MusicItem {
    private String artist;
    private String genre;
    private int duration; // en secondes

<<<<<<< HEAD
    public Song(int id, String title, int releaseYear, String artist, String genre, int duration) {
        super(id, title, releaseYear);
        if (duration < 1 || duration > 36000) {
            throw new IllegalArgumentException("Durée invalide");
        }
=======
    /**
     * Constructeur principal avec des paramètres individuels.
     */
    public Song(int id, String title, int releaseYear, String artist, String genre, int duration) {
        super(id, title, releaseYear);
        if (duration < 1 || duration > 36000)
            throw new IllegalArgumentException("Invalid duration: " + duration);

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

<<<<<<< HEAD
    public String getArtist() {
        return artist;
=======
    /**
     * Constructeur qui prend directement un tableau `parts` (utilisé par `MusicItemFactory`).
     */
    public Song(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.artist = parts[4];
        this.genre = parts[5];
        this.duration = Integer.parseInt(parts[6]);

        if (duration < 1 || duration > 36000)
            throw new IllegalArgumentException("Invalid duration: " + duration);
    }

    // Getters et Setters
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) {
        if (duration < 1 || duration > 36000)
            throw new IllegalArgumentException("Invalid duration: " + duration);
        this.duration = duration;
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }

    @Override
    public String getInfo() {
<<<<<<< HEAD
        return "Song: " + title + " by " + artist + " (" + genre + ", " + duration + "s)";
=======
        return "Song [ID=" + getId() + ", Title=" + getTitle() + ", Year=" + getReleaseYear() +
                ", Artist=" + artist + ", Genre=" + genre + ", Duration=" + duration + "s]";
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }

    @Override
    public String toCSV() {
<<<<<<< HEAD
        return "song," + id + "," + title + "," + releaseYear + "," + artist + "," + genre + "," + duration;
=======
        return "song," + getId() + "," + getTitle() + "," + getReleaseYear() + "," + artist + "," + genre + "," + duration;
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }
}
