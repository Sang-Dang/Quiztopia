package com.fpt.assignment.controller.action;

import java.io.IOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    public static void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the registration data submitted by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // Add any additional fields as needed

        // Validate the registration data
        boolean isValidRegistration = validateRegistration(username, password, email);

        if (isValidRegistration) {
            // If the registration data is valid, save it to the database
            saveRegistrationData(username, password, email);
            
            // Redirect the user to a success page
            response.sendRedirect(request.getContextPath() + "/registration-success.jsp");
        } else {
            // If the registration data is not valid, display an error message
            request.setAttribute("errorMessage", "Invalid registration data");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private static boolean saveRegistrationData(String username, String password, String email) {
        // TODO: Implement validation logic for registration data
        // For example, you could check if the username and email are unique,
        // if the password meets certain requirements, etc.
        return false;    
    }

    private static boolean validateRegistration(String username, String password, String email) {
        return false;
        // TODO: Implement saving of registration data to the database
        // For example, you could use JDBC to connect to the database and insert a new user record
    }

}
