package com.fpt.assignment.controller;

import com.fpt.assignment.dto.Quiz;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo().substring(1);
        String viewName = null;
        
        switch (action) {
            case "register":
                viewName = "register.jsp";
                break;
            case "login":
                viewName = "login.jsp";
                break;
            case "quiz":
                viewName = "quiz.jsp";
                break;
            case "result":
                viewName = "result.jsp";
                break;
            default:
                // if the action is not recognized, forward to error.jsp
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
                return;
        }
        
        request.getRequestDispatcher("/quizController?action=" + action).forward(request, response);
    }
}

