package com.iskandev.servlets;

import com.iskandev.accountservice.IAccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountServiceServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";
    private static final Logger logger = LogManager.getLogger(AccountServiceServlet.class.getName());
    private final IAccountService accountService;

    public AccountServiceServlet(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET repsonse is received");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(accountService.getUsersLimit());
        resp.setStatus(HttpServletResponse.SC_OK);
        logger.info("GET reponse is sent");
    }
}