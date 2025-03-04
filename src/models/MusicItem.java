package models;

public abstract class MusicItem {
<<<<<<< HEAD
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
=======
    private int id;
    private String title;
    private int releaseYear;
    private boolean isPlaying;

    public MusicItem(int id, String title, int releaseYear) {
        if (id < 1) throw new IllegalArgumentException("Invalid music ID: " + id);
        if (releaseYear < 1850 || releaseYear > 2025)
            throw new IllegalArgumentException("Invalid release year: " + releaseYear);

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isPlaying = false;
    }

<<<<<<< HEAD
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
=======
    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    public boolean isPlaying() { return isPlaying; }

    // Méthodes de lecture
    public void play() {
        isPlaying = true;
        System.out.println("Playing: " + getInfo());
    }

    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Pausing: " + getInfo());
        } else {
            System.out.println("No item to PAUSE.");
        }
    }

    public void stop() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Stopping: " + getInfo());
        } else {
            System.out.println("No item to STOP.");
        }
    }

    public abstract String getInfo();
    public abstract String toCSV();

    @Override
    public String toString() {
        return getInfo();
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }
}
