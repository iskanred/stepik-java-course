package com.iskandev.assignment7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThreads {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ServerSocket server = new ServerSocket(5050);
        System.out.println("Server started");

         while (true) {
            Socket client = server.accept();
            ClientHandlerTask task = new ClientHandlerTask(client);
            executorService.submit(task);
        }
    }
}
