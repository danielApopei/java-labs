package org.example;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    public int PORT = 9999;
    private ServerSocket serverSocket;
    boolean stillGoing = true;
    List<ClientThread> threads = new ArrayList<ClientThread>();
    public GameServer() {
        try{
            serverSocket = new ServerSocket(PORT);
            while(stillGoing) {
                System.out.println("WAITING...");
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, this);
                threads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
