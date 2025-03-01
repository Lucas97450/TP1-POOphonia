package models;

public abstract class MusicItem {
    protected int id;
    protected String title;
    protected int releaseYear;
    protected boolean isPlaying;

    public MusicItem(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isPlaying = false;
    }

    public abstract String getInfo();
    public abstract String toCSV();

    public void play() { isPlaying = true; }
    public void stop() { isPlaying = false; }
}
