package models;

public class Podcast extends MusicItem {
    private String host;
    private int episodeNumber;
    private String topic;

    public Podcast(String[] parts) {
        super(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
        this.host = parts[4];
        this.episodeNumber = Integer.parseInt(parts[6]); // ✅ Inverser avec parts[5]
        this.topic = parts[5]; // ✅ Le topic est ici
    }


    public String getHost() { return host; }

    @Override
    public void play() { isPlaying = true; }
    @Override
    public void pause() { isPlaying = false; }
    @Override
    public void stop() { isPlaying = false; }

    @Override
    public String toString() {
        return title + " by " + host;
    }

    @Override
    public String toCSV() {
        return "podcast," + id + "," + title + "," + releaseYear + "," + host + "," + episodeNumber + "," + topic;
    }
}
