package com.fpt.assignment.controller.page;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.AnswerService;
import com.fpt.assignment.service.QuestionService;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.util.Util;

/**
 *
 * @author User
 */
@WebServlet(name = "DoQuizServlet", urlPatterns = {"/DoQuizServlet"})
public class DoQuizServlet extends HttpServlet {

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
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        password = password == null ? "" : password;
        String error;

        String currentURL = request.getHeader("referer");
        currentURL = Util.removeSuccessAndError(currentURL);

        try {
            UUID quizId = QuizService.loginToQuiz(code, password);
            if(quizId == null) {
                throw new ObjectNotFoundException();
            }
            List<Question> questions = QuestionService.getQuestionsByQuizId(quizId.toString());
            List<Answer> answers = AnswerService.getAnswersByQuestion(questions);
            request.setAttribute("quizId", quizId);
            request.setAttribute("questions", questions);
            request.setAttribute("answers", answers);
            request.getRequestDispatcher("WEB-INF/jsp/user-only/do-quiz.jsp").forward(request, response);
            return;
        } catch(ValidationException ex) {
            ex.printStackTrace();
            error = "Invalid code or password. Please try again.";
        } catch(ObjectNotFoundException ex) {
            ex.printStackTrace();
            error = "Incorrect Quiz Code or Password. Please try again.";
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
