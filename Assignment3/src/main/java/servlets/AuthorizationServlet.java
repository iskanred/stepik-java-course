package servlets;

import accountService.AccountService;

import javax.servlet.http.HttpServlet;

public class AuthorizationServlet extends HttpServlet {
    final AccountService accountService;

    public AuthorizationServlet(AccountService accountService) {
        this.accountService = accountService;
    }
}
