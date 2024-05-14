package org.example;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public int PORT = 9999;
    private ServerSocket serverSocket;
    public GameServer() {
        try{
            serverSocket = new ServerSocket(PORT);
            while(true) {
                System.out.println("WAITING...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("GameServer: ok, I close now!");
                // TODO: send response "Server stopped"
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error while closing server socket: " + e);
        }
    }

}
