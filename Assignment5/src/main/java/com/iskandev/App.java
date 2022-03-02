package com.iskandev;

import com.iskandev.accountservice.AccountService;
import com.iskandev.accountservice.AccountServiceController;
import com.iskandev.accountservice.AccountServiceControllerMBean;
import com.iskandev.accountservice.IAccountService;
import com.iskandev.servlets.AccountServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class App {
    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        // init mBean controller
        IAccountService accountService = new AccountService(10);
        AccountServiceControllerMBean accountServiceController =
                new AccountServiceController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName accountServiceControllerName = new ObjectName("Admin:type=AccountServerController");
        mBeanServer.registerMBean(accountServiceController, accountServiceControllerName);
        //mBeanServer.setAttribute(accountServiceControllerName, new Attribute("usersLimit", 10));

       // init server with servlet
        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new AccountServiceServlet(accountService)),
                AccountServiceServlet.PAGE_URL);
        server.setHandler(servletContextHandler);
        server.start();

        logger.info("Server started");
        System.out.println("Server started");

    }
}
