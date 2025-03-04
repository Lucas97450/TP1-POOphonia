package models;

public abstract class MusicItem {
    protected int id;
    protected String title;
    protected int releaseYear;
    protected boolean isPlaying;

    public MusicItem(int id, String title, int releaseYear) {
        if (id <= 0 || releaseYear < 1850) {
            throw new IllegalArgumentException("Invalid ID or release year");
        }
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isPlaying = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }

    public abstract void play();
    public abstract void pause();
    public abstract void stop();
    public abstract String toString();

    // 🔥 Méthode à implémenter dans les sous-classes
    public abstract String toCSV();
}
