package models;

public class Album extends MusicItem {
    private String artist;
    private String label;
    private int numberOfTracks;

    public Album(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.artist = parts[4];
        this.label = parts[5];
        this.numberOfTracks = Integer.parseInt(parts[6]);
    }

    public String getArtist() { return artist; }

    @Override
    public void play() { isPlaying = true; }
    @Override
    public void pause() { isPlaying = false; }
    @Override
    public void stop() { isPlaying = false; }

    @Override
    public String toString() {
        return title + " by " + artist;
    }

    @Override
    public String toCSV() {
        return "album," + id + "," + title + "," + releaseYear + "," + artist + "," + label + "," + numberOfTracks;
    }
}
