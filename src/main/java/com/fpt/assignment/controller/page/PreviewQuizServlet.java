package com.fpt.assignment.controller.page;

import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.service.UserService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "PreviewQuizServlet", urlPatterns = {"/PreviewQuizServlet"})
public class PreviewQuizServlet extends HttpServlet {

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
        String quizCode = request.getParameter("id");
        String error;

        String currentURL = request.getHeader("referer");
        currentURL = Util.removeSuccessAndError(currentURL);

        try {
            Quiz currentQuiz = QuizService.getQuizByCode(quizCode);
            if(currentQuiz == null) {
                throw new ObjectNotFoundException();
            }
            int questions = QuizService.getNumberOfQuestions(currentQuiz.getId().toString());
            String user = UserService.getSafeUserById(currentQuiz.getUser_id()).getUsername();
            request.setAttribute("creator", user);
            request.setAttribute("noquestions", questions);
            request.setAttribute("currentQuiz", currentQuiz);
            request.getRequestDispatcher("WEB-INF/jsp/user-only/preview-quiz.jsp").forward(request, response);
            return;
        } catch (ValidationException ex) {
            ex.printStackTrace();
            error = "Invalid quiz code.";
        } catch (ObjectNotFoundException ex) {
            ex.printStackTrace();
            error = "Quiz not found.";
        }
        currentURL = Util.addURLParameters(currentURL, "error", error);
        response.sendRedirect(currentURL);
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
