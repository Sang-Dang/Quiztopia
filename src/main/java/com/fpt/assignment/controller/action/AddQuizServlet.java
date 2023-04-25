package com.fpt.assignment.controller.action;

import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.service.QuestionService;
import com.fpt.assignment.service.QuizService;
import java.io.IOException;
import java.util.Arrays;
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
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        UUID userId = (UUID) session.getAttribute("currentUserId");

        String[] questions = request.getParameterValues("question");
        String[] correctAnswers = request.getParameterValues("answer");

        System.out.println(title + " " + description + " " + password);
        System.out.println("Questions:");
        for (String i : questions) {
            System.out.println(i);
        }
        for (int i = 0; i < questions.length; i++) {
            String current = null;
            int index = 1;
            do {
                current = request.getParameter(String.format(ANSWER_FORMAT, i, index));
                System.out.println(String.format(ANSWER_FORMAT, i, index));
                System.out.println(current);
                index++;
            } while (current != null);
        }

//        List<String> correct = Arrays.asList(correctAnswers);
//        if (password.isEmpty()) {
//            password = null;
//        }
//        int index = 0;
//        for (String i : questions) {
//            // create a question 
////            UUID questionId = QuestionService.createQuestion(quizId.toString(), i);
//            // add answers
//            int index1 = 0;
//            do {
//                String answer = request.getParameter(String.format(ANSWER_FORMAT, index, index1));
//                if (answer == null) {
//                    break;
//                }
//                System.out.println(answer);
//                index1++;
//            } while (true);
//            index++;
//        }
//        try {
//            // create the quiz
//            UUID quizId = QuizService.createQuiz(userId.toString(), title, description, password);
//            int index = 0;
//            for(String i: questions) {
//                // create a question 
//                UUID questionId = QuestionService.createQuestion(quizId.toString(), i);
//                // add answers
//                int index1 = 0;
//                do {
//                    String answer = request.getParameter(String.format(ANSWER_FORMAT, index, index1));
//                    if(answer == null) {
//                        break;
//                    }
//                    System.out.println(answer);
//                    index1++;
//                } while(true);
//                index++;
//            }
//        } catch (ValidationException ex) {
//            Logger.getLogger(AddQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
