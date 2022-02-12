package websockets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public class MessageWebSocket {

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connection is established: " + session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("message " + message);
        session.getRemote().sendString(message);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Connection is closed: " + session + ". With the status code: " + statusCode);
        System.out.println(reason);
    }

    @OnWebSocketError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
