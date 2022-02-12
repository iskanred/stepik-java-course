package main;

import accountService.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import org.h2.jdbcx.JdbcDataSource;
import static dbService.DBService.*;

public class Main {
    public static void main(String[] args) throws Exception {
        JdbcDataSource db = new JdbcDataSource();
        db.setURL(url);
        db.setUser(username);
        db.setPassword(password);

        AccountService accountService = new AccountService();

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        servletContextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
        System.out.println("Server started"); // log
        server.join();
    }
}
