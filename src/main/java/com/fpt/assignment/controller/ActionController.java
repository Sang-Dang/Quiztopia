package com.fpt.assignment.controller;


import com.fpt.assignment.controller.action.LoginServlet;
import com.fpt.assignment.controller.action.RegisterServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionController {
    public static void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination = "error.jsp";
        
        if (action == null) {
            // default action
            destination = "index.jsp";
        } else if (action.equals("login")) {
            // handle login action
            LoginServlet.login(request, response);
            return; // stop execution of this method after login is complete
        } else if (action.equals("register")) {
            // handle register action
            RegisterServlet.register(request, response);
            return; // stop execution of this method after registration is complete
        } else {
            // handle unknown action
            request.setAttribute("errorMessage", "Invalid action");
        }
        
        // forward to appropriate JSP
        request.getRequestDispatcher(destination).forward(request, response);
    }
}
