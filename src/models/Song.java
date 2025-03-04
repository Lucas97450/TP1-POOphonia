package models;

public class Song extends MusicItem {
    private String artist;
    private String genre;
    private int duration;

    public Song(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.artist = parts[4];
        this.genre = parts[5];
        this.duration = Integer.parseInt(parts[6]);
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
        return "song," + id + "," + title + "," + releaseYear + "," + artist + "," + genre + "," + duration;
    }
}

