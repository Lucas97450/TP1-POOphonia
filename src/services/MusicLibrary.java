package services;
import models.MusicItem;
import java.util.ArrayList;

public class MusicLibrary {
    private ArrayList<MusicItem> items;

    public MusicLibrary() {
        this.items = new ArrayList<>();
    }

    public void addItem(MusicItem item) {
        items.add(item);
    }

    public void listAllItems() {
        for (MusicItem item : items) {
            System.out.println(item.getInfo());
        }
    }
}
