package models;

public class Podcast extends MusicItem {
    private String host;
    private int episodeNumber;
    private String topic;

    public Podcast(int id, String title, int releaseYear, String host, String topic, int episodeNumber) {
        super(id, title, releaseYear);
        if (episodeNumber < 1 || episodeNumber > 500) {
            throw new IllegalArgumentException("Numéro d'épisode invalide");
        }
        this.host = host;
        this.episodeNumber = episodeNumber;
        this.topic = topic;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String getInfo() {
        return "Podcast: " + title + " hosted by " + host + " (" + topic + ", Episode " + episodeNumber + ")";
    }

    @Override
    public String toCSV() {
        return "podcast," + id + "," + title + "," + releaseYear + "," + host + "," + topic + "," + episodeNumber;
    }
}
