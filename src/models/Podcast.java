package models;

public class Podcast extends MusicItem {
    private String host;
    private int episodeNumber;
    private String topic;

<<<<<<< HEAD
    public Podcast(int id, String title, int releaseYear, String host, String topic, int episodeNumber) {
        super(id, title, releaseYear);
        if (episodeNumber < 1 || episodeNumber > 500) {
            throw new IllegalArgumentException("Numéro d'épisode invalide");
        }
=======
    /**
     * Constructeur principal avec des paramètres individuels.
     */
    public Podcast(int id, String title, int releaseYear, String host, int episodeNumber, String topic) {
        super(id, title, releaseYear);
        if (episodeNumber < 1 || episodeNumber > 500)
            throw new IllegalArgumentException("Invalid episode number: " + episodeNumber);

>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
        this.host = host;
        this.episodeNumber = episodeNumber;
        this.topic = topic;
    }

<<<<<<< HEAD
    public String getHost() {
        return host;
    }

    @Override
    public String getInfo() {
        return "Podcast: " + title + " hosted by " + host + " (" + topic + ", Episode " + episodeNumber + ")";
=======
    /**
     * Constructeur qui prend directement un tableau `parts` (utilisé par `MusicItemFactory`).
     */
    public Podcast(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.host = parts[4];
        this.topic = parts[5]; // ⚠️ Vérifier l'ordre avec MusicItemFactory
        this.episodeNumber = Integer.parseInt(parts[6]);

        if (episodeNumber < 1 || episodeNumber > 500)
            throw new IllegalArgumentException("Invalid episode number: " + episodeNumber);
    }


    // Getters et Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(int episodeNumber) {
        if (episodeNumber < 1 || episodeNumber > 500)
            throw new IllegalArgumentException("Invalid episode number: " + episodeNumber);
        this.episodeNumber = episodeNumber;
    }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    @Override
    public String getInfo() {
        return "Podcast [ID=" + getId() + ", Title=" + getTitle() + ", Year=" + getReleaseYear() +
                ", Host=" + host + ", Episode=" + episodeNumber + ", Topic=" + topic + "]";
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }

    @Override
    public String toCSV() {
<<<<<<< HEAD
        return "podcast," + id + "," + title + "," + releaseYear + "," + host + "," + topic + "," + episodeNumber;
=======
        return "podcast," + getId() + "," + getTitle() + "," + getReleaseYear() + "," + host + "," + episodeNumber + "," + topic;
>>>>>>> 8ddaad4 (Probleme CLEAR, LOAD, Play apres un SEARCH)
    }
}
