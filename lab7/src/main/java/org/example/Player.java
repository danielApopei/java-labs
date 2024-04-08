package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * represents a player
 */
public class Player implements Runnable{
    private String name;
    private Game game;
    private boolean running;
    private List<Tile> tiles = new ArrayList<>();
    int preferenceX = -1, preferenceY = -1;
    public void setGame (Game game) {
        this.game = game;
    }
    public Player(String name) {this.name = name;}

    public Tile extractTile() {
        Bag bag = game.getBag();

        // see what pairs there are in graph
        int[] exteriorDegree = new int[game.highNumber];
        int[] numbers = new int[game.highNumber];
        for(int i=0;i<game.highNumber;i++)
            numbers[i] = i;
        // calculate exterior degree for each number
        for(Tile tile : bag.tileList)
            exteriorDegree[tile.x-1] += 1;
        // sort descending
        for(int i=0;i<game.highNumber-1;i++) {
            for(int j=i+1;j<game.highNumber;j++) {
                if(exteriorDegree[i]>exteriorDegree[j]) {
                    int aux = exteriorDegree[i];
                    exteriorDegree[i] = exteriorDegree[j];
                    exteriorDegree[j] = aux;
                    aux = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = aux;
                }
            }
        }

        for(int i=0;i<game.highNumber;i++) {
            for(Tile tile : bag.tileList) {
                if(tile.x-1 == numbers[i]) {
                    tiles.add(tile);
                    bag.tileList.remove(tile);
                    return tile;
                }
            }
        }
        return null;
    }
    public void run() {
        System.out.println(name + " began extracting!");
        running = true;
        synchronized (game) {
            while(running && game.gameRunning) {
                if(game.getBag().isEmpty()) {
                    running = false;
                    game.notifyAll();
                    return;
                }
                Tile extractedTile = extractTile();
                System.out.println(name + " extracted: "+ extractedTile);
                game.notifyAll();
                try{
                    game.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(name + "was interrupted.");
                }
            }
        }
        System.out.println("done!");
    }

    public int dfs(int n, int[][] adjacencyMatrix, int startingNode, int depth, int currentNode) {
        int length = 0;
        if(adjacencyMatrix[currentNode][startingNode] > 0)
            length = depth + 1;
        for(int i=0;i<n;i++) {
            if(i == currentNode) continue;
            if(adjacencyMatrix[currentNode][i]>0) {
                adjacencyMatrix[currentNode][i] -= 1;
                length = Math.max(length, dfs(n, adjacencyMatrix, startingNode, depth + 1, i));
            }
        }
        return length;
    }

    public int calculateScore(int highNumber) {
        // generate adj matrix with hN rows and cols
        int [][] adjacencyMatrix = new int[highNumber][highNumber];

        // consider each token to be an edge (multi-graph)
        for(Tile tile :tiles) {
            adjacencyMatrix[tile.x-1][tile.y-1] += 1;
        }

        // create a copy of adjacencyMatrix for backup
        int[][] auxMatrix = new int[adjacencyMatrix.length][adjacencyMatrix[0].length]; // Create a new array with the same dimensions
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                auxMatrix[i][j] = adjacencyMatrix[i][j]; // Copy each element from the original array to the new array
            }
        }


        // execute N * dfs while tracking the level and whether i have road to start
        int longestSequence = 0;
        for(int z=0;z<highNumber;z++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                    adjacencyMatrix[i][j] = auxMatrix[i][j]; // Copy each element from the original array to the new array
                }
            }
            int tempLongestSequence = dfs(highNumber, adjacencyMatrix, z, 0, z);
            System.out.println("Score2: " + tempLongestSequence);
            if(tempLongestSequence > longestSequence)
                longestSequence = tempLongestSequence;
        }
        return longestSequence;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", tiles=" + tiles +
                '}';
    }
}
