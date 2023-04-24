package com.fpt.assignment.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    public static void login(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        // Get the username and password submitted by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the username and password against the database
        boolean isValidUser = validateUser(username, password);

        if (isValidUser) {
            // If the user is valid, create a new session for them
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);

            // Redirect the user to the home page
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            // If the user is not valid, display an error message
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private static boolean validateUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
