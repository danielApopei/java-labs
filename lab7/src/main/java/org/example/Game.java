package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();

    public Bag getBag() {
        return bag;
    }

    private final List<Player> players = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();
    public void generateGame(){
        bag.generateBag();
    }
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() throws InterruptedException {
        for(Player player : players) {
            Thread t = new Thread(player);
            threads.add(t);
            t.start(); // wtf is this?
        }
        for(Thread thread : threads) {
            thread.join();
        }
    }

    public void showHands() {
        for(Player player : players) {
            player.showHand();
        }
    }
}
