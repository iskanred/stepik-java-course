package com.iskandev.assignment7;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5050);
        Socket socket2 = new Socket("localhost", 5050);
        Socket socket3 = new Socket("localhost", 5050);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
        BufferedReader in3 = new BufferedReader(new InputStreamReader(socket3.getInputStream()));
        BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(socket3.getOutputStream()));

        out.write("Hello1\n");
        out.flush();
        System.out.println(in.readLine());
        out.write("Bye");

        in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        out2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
        out2.write("Hello2\n");
        out2.flush();
        System.out.println(in2.readLine());

        in3 = new BufferedReader(new InputStreamReader(socket3.getInputStream()));
        out3 = new BufferedWriter(new OutputStreamWriter(socket3.getOutputStream()));
        out3.write("Hello3\n");
        out3.flush();
        System.out.println(in3.readLine());

        out.write("Hello1 one more time");
        out2.write("H@llo2 one");
        out3.write("DEded");


        out.write("Bye");
        out2.write("Bye");
        out3.write("Bye");

        out.flush();
        out2.flush();
        out3.flush();
    }
}
