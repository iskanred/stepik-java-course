package servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import websockets.MessageWebSocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static javax.servlet.http.HttpServletResponse.SC_OK;


@WebServlet(name = "MessageWebSocketServlet", urlPatterns = {"/chat"})
public class MessageWebSocketServlet extends WebSocketServlet {
    private final static Long TIMEOUT = TimeUnit.MINUTES.toMillis(5);

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(TIMEOUT);
        webSocketServletFactory.setCreator((req, resp) -> new MessageWebSocket());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        try (BufferedReader br = new BufferedReader(new FileReader("public_html/index.html"))) {
            String line;
            while ((line = br.readLine()) != null) {
                resp.getWriter().println(line);
            }
        }

        resp.setStatus(SC_OK);
    }
}
