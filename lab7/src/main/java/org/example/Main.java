package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.generateGame(30, 10);
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
        game.calculateWinner();
    }
}