package servlets;

import accountService.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SignUpServlet extends AuthorizationServlet {

    public SignUpServlet(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        try (BufferedReader br = new BufferedReader(new FileReader("public_html/signup.html"))) {
            String line;
            while ((line = br.readLine()) != null) {
                resp.getWriter().println(line);
            }
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println(login + " " + password);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!accountService.addNewUser(login, password))
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        else
            resp.setStatus(HttpServletResponse.SC_OK);
    }
}
