package com.iskandev.assignment7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerNIO {
    public static void main(String[] args) throws IOException {
        Selector selector;
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel =
            ServerSocketChannel.open().bind(new InetSocketAddress("localhost", 5050));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,
            SelectionKey.OP_ACCEPT);

        System.out.println("Server started");

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel clientSocketChannel = serverSocketChannel.accept();
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel clientSocketChannel = (SocketChannel) key.channel();
                    String message = readMessage(clientSocketChannel);

                    if (message.contains("Bue") || message.contains("Bye")) {
                        clientSocketChannel.close();
                    } else {
                        writeMessage(clientSocketChannel, message + "\n");
                    }
                }
                iterator.remove();
            }
        }
    }

    private static String readMessage(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        StringBuilder message = new StringBuilder();
        socketChannel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) { message.append((char) buffer.get()); }
        buffer.clear();

        return message.toString();
    }

    private static void writeMessage(SocketChannel socketChannel, String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put(message.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }
}
