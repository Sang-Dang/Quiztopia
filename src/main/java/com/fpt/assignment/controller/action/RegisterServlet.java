package com.fpt.assignment.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    String email = request.getParameter("email");
    
    // Validate user input
    if (username == null || username.trim().isEmpty() ||
        password == null || password.trim().isEmpty() ||
        confirmPassword == null || confirmPassword.trim().isEmpty() ||
        email == null || email.trim().isEmpty()) {
      // Display an error message if any fields are empty
      request.setAttribute("error", "All fields are required.");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
      dispatcher.forward(request, response);
    } else if (!password.equals(confirmPassword)) {
      // Display an error message if the password and confirm password do not match
      request.setAttribute("error", "Passwords do not match.");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
      dispatcher.forward(request, response);
    } else {
      // Create a new user object and save it to the database
      User user = new User(username, password, email);
      UserDao userDao = new UserDao();
      userDao.addUser(user);
      
      // Set the user object as a session attribute and redirect to the home page
      request.getSession().setAttribute("user", user);
      response.sendRedirect(request.getContextPath() + "/home");
    }
    }

}
