package org.example;

import java.awt.*;

/**
 * class representing a stone a player can place
 */
public class Stone {
    public int x;
    public int y;
    public Color player;
    Stone(int x, int y, Color player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
}
