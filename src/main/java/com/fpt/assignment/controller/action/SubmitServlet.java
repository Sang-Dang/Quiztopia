package com.fpt.assignment.controller.action;

import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.service.ResultService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "SubmitServlet", urlPatterns = {"/SubmitServlet"})
public class SubmitServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error, success;
        String[] questionIds = request.getParameterValues("questionId");
        List<String> questions = Arrays.asList(questionIds);
        HashMap<String, ArrayList<String>> answerSet = new HashMap<>();
        for(String i: questions) {
            String[] chosenAnswers = request.getParameterValues(i);
            ArrayList<String> answers = new ArrayList<>(Arrays.asList(chosenAnswers));
            answerSet.put(i, answers);
        }
        
        HttpSession session = request.getSession(false);
        UUID userId = (UUID) session.getAttribute("currentUserId");
        String quizId = request.getParameter("quizId");
        
        try {
            int result = QuizService.calculateResults(answerSet);
            ResultService.saveResult(result, quizId, userId.toString());
            success = "Attempt finished. Check out your score!";
            response.sendRedirect("home?page=view-results&success=" + Util.encode(success));
            return;
        } catch (ValidationException ex) {
            error = "Something went wrong with your quiz. Please reattempt the quiz.";
            ex.printStackTrace();
        }
        response.sendRedirect("home?error=" + Util.encode(error));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
