package models;

public class Song extends MusicItem {
    private String artist;
    private String genre;
    private int duration; // en secondes

    public Song(int id, String title, int releaseYear, String artist, String genre, int duration) {
        super(id, title, releaseYear);
        if (duration < 1 || duration > 36000) {
            throw new IllegalArgumentException("Durée invalide");
        }
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String getInfo() {
        return "Song: " + title + " by " + artist + " (" + genre + ", " + duration + "s)";
    }

    @Override
    public String toCSV() {
        return "song," + id + "," + title + "," + releaseYear + "," + artist + "," + genre + "," + duration;
    }
}
