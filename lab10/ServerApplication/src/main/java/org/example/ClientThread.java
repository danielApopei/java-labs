package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    public Socket socket = null;
    public GameServer server = null;
    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String request;
            while ((request = in.readLine()) != null) {
                String response = handleRequest(request);
                out.println(response);
                out.flush();
                if(request.equals("exit")) {
                    System.out.println("Ok, man! See ya!");
                    for(ClientThread clientThread: server.threads) {
                        if(clientThread != this) {
                            server.threads.remove(clientThread);
                        }
                    }
                    break;
                }
                if(request.equals("stop")) {
                    System.out.println("ClientThread: stopping server");
                    server.stopServer();
                    break;
                }
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Client probably disconnected!");
        } finally {
            try{
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private String handleRequest(String request) {
        if(request.equals("exit")) {
            return "Server stopped";
        }
        return "Received request: " + request;
    }
}
