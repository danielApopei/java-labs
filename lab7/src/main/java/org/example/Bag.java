package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * the collection of tiles that can be extracted
 */
public class Bag {
    public final List<Tile> tileList = new ArrayList<>();

    public boolean isEmpty() {
        return tileList.isEmpty();
    }
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for(int i=0;i<howMany;i++) {
            if(tileList.isEmpty())
                break;
            int randomIndex = (int) (Math.random() * tileList.size());
            extracted.add(tileList.get(randomIndex));
            tileList.remove(randomIndex);
        }
        return extracted;
    }

    public void generateBag() {
        tileList.clear();
        int n = (int) (Math.random() * 30);
        for(int i=0;i<n;i++) {
            int x = (int)(1 + Math.random() * 10);
            int y = (int)(1 + Math.random() * 10);
            Tile t = new Tile(x,y);
            tileList.add(t);
        }
    }
}
