package models;

public class Album extends MusicItem {
    private String artist;
    private int numberOfTracks;
    private String label;

    public Album(int id, String title, int releaseYear, String artist, String label, int numberOfTracks) {
        super(id, title, releaseYear);
        if (numberOfTracks < 1 || numberOfTracks > 100) {
            throw new IllegalArgumentException("Nombre de pistes invalide");
        }
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
        this.label = label;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String getInfo() {
        return "Album: " + title + " by " + artist + " (" + numberOfTracks + " tracks, " + label + ")";
    }

    @Override
    public String toCSV() {
        return "album," + id + "," + title + "," + releaseYear + "," + artist + "," + label + "," + numberOfTracks;
    }
}
