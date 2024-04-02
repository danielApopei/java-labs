package org.example;

import java.io.Serializable;
import java.awt.Color;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    public int lastX, lastY;
    public ArrayList<Stone> stones;
    public ArrayList<Road> roads;
    public Color currentPlayer;

    public GameState(int lastX, int lastY, ArrayList<Stone> stones, ArrayList<Road> roads, Color currentPlayer) {
        this.lastX = lastX;
        this.lastY = lastY;
        this.stones = stones;
        this.roads = roads;
        this.currentPlayer = currentPlayer;
    }

}
