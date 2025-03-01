package models;

public abstract class MusicItem {
    protected int id;
    protected String title;
    protected int releaseYear;
    protected boolean isPlaying;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }


    public MusicItem(int id, String title, int releaseYear) {
        if (id <= 0 || releaseYear < 1850 || releaseYear > 2025) {
            throw new IllegalArgumentException("ID invalide ou année hors limite");
        }
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isPlaying = false;
    }

    public abstract String getInfo();
    public abstract String toCSV();

    public void play() {
        isPlaying = true;
    }

    public void stop() {
        isPlaying = false;
    }

    @Override
    public String toString() {
        return id + " - " + title + " (" + releaseYear + ")";
    }
}
