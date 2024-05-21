package org.example;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameServer {
    public int PORT = 9999;
    private ServerSocket serverSocket;
    boolean stillGoing = true;
    final int secretNumber;
    List<ClientThread> threads = new ArrayList<ClientThread>();
    public GameServer() {
        Random random = new Random();
        secretNumber = random.nextInt(30);
        System.out.println("Secret number is: " + secretNumber);
        try{
            serverSocket = new ServerSocket(PORT);
            while(stillGoing) {
                System.out.println("WAITING...");
                Socket socket = serverSocket.accept();
                sendWelcomeMessage(socket);
                ClientThread clientThread = new ClientThread(socket, this, secretNumber);
                threads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendWelcomeMessage(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Welcome to the game!");
        out.flush();
    }

    public synchronized void stopServer() {
        try {
            for(ClientThread clientThread : threads) {
                Socket thisSocket = clientThread.socket;
                if (thisSocket != null && !thisSocket.isClosed()) {
                    System.out.println("GameServer: ok, I close now!");
                    // TODO: send response "Server stopped"
                    thisSocket.close();
                    stillGoing = false;
                }

            }
        } catch (IOException e) {
            System.err.println("Error while closing server socket: " + e);
        }
    }

}
