# Goal
The goal is to learn how to work with Multithread/NIO and TCP sockets.

During testing, 10 clients will connect to the server at the same time. 
Each client will send a message every millisecond for 5 seconds. 
The server must respond to all clients at the same time. 
The time to process messages from all clients is no more than 10 seconds.

# Used technologies
Java 8, Sockets, Threads, NIO
