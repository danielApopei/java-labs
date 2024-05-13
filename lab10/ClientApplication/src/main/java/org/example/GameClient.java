package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public GameClient() {
        String serverAddress = "127.0.0.1";
        int serverPort = 9999;
        try (
                Socket socket = new Socket(serverAddress, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
                ) {
            while(true) {
                System.out.println("> ");
                String input = scanner.nextLine();
                out.println(input);
                String response = in.readLine();
                if(response.equals("Server stopped")) {
                    System.out.println("Server said bye bye!");
                    break;
                }
                System.out.println(response);
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("Server probably offline");
        }
    }
}
