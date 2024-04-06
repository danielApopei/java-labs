package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable{
    private String name;
    private Game game;
    private boolean running;
    private List<Tile> tiles = new ArrayList<>();
    public void setGame (Game game) {
        this.game = game;
    }
    public Player(String name) {this.name = name;}
    public void run() {
        System.out.println(name + " began extracting!");
        running = true;
        while(running) {
            synchronized (game) {
                if(game.getBag().isEmpty()) {
                    running = false;
                    game.notifyAll();
                    return;
                }
                Tile extractedTile = game.getBag().extractTiles(1).get(0);
                System.out.println(name + " extracted: "+ extractedTile);
                tiles.add(extractedTile);
                game.notifyAll();
                try{
                    game.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(name + "was interrupted.");
                }
            }
        }

    }

    public void showHand() {
        System.out.println(name + ": I have - " + tiles);
    }
}
