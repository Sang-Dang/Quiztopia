package com.fpt.assignment.controller.page;

import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.AnswerService;
import com.fpt.assignment.service.QuestionService;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ViewQuizServlet", urlPatterns = {"/ViewQuizServlet"})
public class ViewQuizServlet extends HttpServlet {

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
        String error;
        String quizCode = request.getParameter("id");
        HttpSession session = request.getSession(false);
        UUID loggedIn = (UUID)session.getAttribute("currentUserId");
        try {
            Quiz currentQuiz = QuizService.getQuizByCode(quizCode);
            UUID quizCreator = currentQuiz.getUser_id();
            if(!loggedIn.equals(quizCreator)) {
                throw new ObjectNotFoundException();
            }
            List<Question> questions = QuestionService.getQuestionsByQuizId(currentQuiz.getId().toString());
            List<Answer> answers = new ArrayList<>();
            for(Question i: questions) {
                answers.addAll(AnswerService.getAnswersByQuestionId(i.getId().toString()));
            }
            request.setAttribute("quiz", currentQuiz);
            request.setAttribute("questions", questions);
            request.setAttribute("answers", answers);
            request.getRequestDispatcher("WEB-INF/jsp/user-only/view-quiz.jsp").forward(request, response);
            return;
        } catch (ValidationException ex) {
            error = ex.getMessage();
            ex.printStackTrace();
        } catch (ObjectNotFoundException ex) {
            error = "You are not allowed to view this.";
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
