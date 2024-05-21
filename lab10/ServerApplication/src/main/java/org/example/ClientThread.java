package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class ClientThread extends Thread {
    public Socket socket = null;
    public GameServer server = null;
    private int secretNumber;
    public ClientThread(Socket socket, GameServer server, int secretNumber) {
        this.socket = socket;
        this.server = server;
        this.secretNumber = secretNumber;
    }
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String request;
            while (true) {
                request = in.readLine();
                String response = handleRequest(request);
                out.println("Incearca sa ghicesti numarul secret!");
                out.flush();
                if(request.equals("exit")) {
                    System.out.println("Ok, man! See ya!");
                    Iterator<ClientThread> iterator = server.threads.iterator();
                    while (iterator.hasNext()) {
                        ClientThread clientThread = iterator.next();
                        if(clientThread != this) {
                            iterator.remove();
                        }
                    }
                    socket.close();
                    break;
                }
                if(isNumeric(request) && Integer.parseInt(request)==secretNumber) {
                    System.out.println("Congrats man, you found the number!");
                    Iterator<ClientThread> iterator = server.threads.iterator();
                    while (iterator.hasNext()) {
                        ClientThread clientThread = iterator.next();
                        if(clientThread != this) {
                            iterator.remove();
                        }
                    }
                    socket.close();
                    break;
                }
                if(request.equals("stop")) {
                    System.out.println("ClientThread: stopping server");
                    server.stopServer();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client probably disconnected!");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private String handleRequest(String request) {
        if(request.equals("exit")) {
            return "Server stopped";
        }
        return "Received request: " + request;
    }
}
