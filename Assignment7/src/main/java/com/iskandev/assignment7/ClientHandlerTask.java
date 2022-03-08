package com.iskandev.assignment7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandlerTask implements Runnable {
    private final Socket socket;

    public ClientHandlerTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = in.readLine();
            while (message != null && !message.contains("Bye") && !message.contains("Bue")) {
                out.println(message);
                out.flush();
                message = in.readLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
