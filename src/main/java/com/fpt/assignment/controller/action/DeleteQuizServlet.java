package com.fpt.assignment.controller.action;

import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
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
@WebServlet(name = "DeleteQuizServlet", urlPatterns = {"/DeleteQuizServlet"})
public class DeleteQuizServlet extends HttpServlet {

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
        String success, error;
        HttpSession session = request.getSession(false);
        UUID userID = (UUID) session.getAttribute("currentUserId");
        String code = request.getParameter("id");
        try {
            Quiz current = QuizService.getQuizByCode(code);
            if (!current.getUser_id().equals(userID)) {
                throw new ObjectNotFoundException();
            }
            
            QuizService.deleteQuiz(current.getId().toString());
            
            success = "Delete successful";
            response.sendRedirect("home?page=manage-quizzes&success=" + Util.encode(success));
            return;
        } catch (ValidationException ex) {
            error = "Invalid code";
            ex.printStackTrace();
        } catch (ObjectNotFoundException ex) {
            error = "You are not allowed to do that";
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
