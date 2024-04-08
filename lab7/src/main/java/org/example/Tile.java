package org.example;

/**
 * represents a Tile
 */
public class Tile {
    public int x;
    public int y;
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x +
                ", " + y +
                ']';
    }
}
