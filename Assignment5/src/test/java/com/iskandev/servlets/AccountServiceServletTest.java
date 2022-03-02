package com.iskandev.servlets;

import com.iskandev.accountservice.AccountService;
import com.iskandev.accountservice.IAccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceServletTest {
    private HttpServletRequest getMockRequest(String URL) {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn(URL);

        return request;
    }

    private HttpServletResponse getMockResponse(StringWriter writer) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        var printWriter = new PrintWriter(writer);
        when(response.getWriter()).thenReturn(printWriter);

        return response;
    }

    @Test
    void tesDoGet() throws IOException, ServletException {
        var writer = new StringWriter();
        var request = getMockRequest(AccountServiceServlet.PAGE_URL);
        var response = getMockResponse(writer);
        IAccountService accountService = mock(AccountService.class);

        var accountServiceServlet = new AccountServiceServlet(accountService);
        accountServiceServlet.doGet(request, response);

        assertEquals(10, Integer.parseInt(writer.toString().trim()));
        verify(accountService, times(1)).getUsersLimit();
    }
}
