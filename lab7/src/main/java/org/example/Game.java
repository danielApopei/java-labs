package org.example;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * an instance of the token-picking game
 */
public class Game {
    private final Bag bag = new Bag();
    int highNumber;
    volatile boolean gameRunning;

    public Bag getBag() {
        return bag;
    }

    private final List<Player> players = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();
    private class TimeKeeper extends Thread {
        long startTime;
        long timeLimit;
        public TimeKeeper(long timeLimit) {
            this.startTime = System.currentTimeMillis();
            this.timeLimit = timeLimit;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (gameRunning) {
                long elapsed = System.currentTimeMillis() - startTime;
                System.out.println("TK: time elapsed " + elapsed);
                if (elapsed > timeLimit) {
                    System.out.println("Time limit exceeded. Stopping game.");
                    stopGame(); // Method to safely stop the game
                    break;
                }
                try {
                    Thread.sleep(20); // Update interval
                } catch (InterruptedException e) {
                    System.out.println("Timekeeper interrupted.");
                }
            }
        }
    }

    private void stopGame() {
        gameRunning = false;
        for(Thread t : threads)
            t.interrupt();
    }
    public void generateGame(int tileCount, int highNumber){
        bag.generateBag(tileCount, highNumber);
        this.highNumber = highNumber;
    }
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() throws InterruptedException {
        TimeKeeper timeKeeper = new TimeKeeper(30);
        timeKeeper.start();
        gameRunning = true;
        for(Player player : players) {
            Thread t = new Thread(player);
            threads.add(t);
            t.start(); // wtf is this?
        }
        for(Thread thread : threads) {
            thread.join();
        }
        gameRunning = false;
        timeKeeper.join();
    }

    public void calculateWinner() {
        int highScore = 0;
        Player winner = null;
        for(Player player : players) {
            int score = player.calculateScore(highNumber);
            System.out.println("Score1: " + score);
            if(score > highScore) {
                highScore = score;
                winner = player;
            }
        }
        System.out.println("Highest score: "+ highScore);
        System.out.println("Winner: " + winner);
    }
}
