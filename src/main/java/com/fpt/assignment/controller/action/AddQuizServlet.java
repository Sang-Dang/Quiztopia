package com.fpt.assignment.controller.action;

import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.AnswerService;
import com.fpt.assignment.service.QuestionService;
import com.fpt.assignment.service.QuizService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "AddQuizServlet", urlPatterns = {"/AddQuizServlet"})
public class AddQuizServlet extends HttpServlet {

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
        final String ANSWER_FORMAT = "question_%s_answer_%s";
        String success, error;
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        UUID userId = (UUID) session.getAttribute("currentUserId");

        String[] questions = request.getParameterValues("question");
        String[] correctAnswers = request.getParameterValues("answer");
        List<String> correct = new ArrayList<>();
        if (correctAnswers != null) {
            correct = Arrays.asList(correctAnswers);
        }

        try {
            UUID quizId = QuizService.createQuiz(userId.toString(), title, description, password);

            for (int i = 0; i < questions.length; i++) {
                UUID questionId = QuestionService.createQuestion(quizId.toString(), questions[i]);

                String currentAnswer = null;
                int index = 1;
                do {
                    String currentAnswerName = String.format(ANSWER_FORMAT, i, index);
                    currentAnswer = request.getParameter(currentAnswerName);
                    if (currentAnswer != null) {
                        System.out.println("Adding answer: \"" + currentAnswerName + "\" with value " + currentAnswer + ".");
                        AnswerService.createAnswer(questionId.toString(), currentAnswer, correct.contains(currentAnswerName));
                    }
                    index++;
                } while (currentAnswer != null);
            }
            success = "Quiz created!";
            response.sendRedirect("home?page=manage-quizzes&success=" + Util.encode(success));
            return;
        } catch (ValidationException ex) {
            ex.printStackTrace();
            error = ex.getMessage();
        }
        response.sendRedirect("home?page=add-quiz&error=" + Util.encode(error));
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
