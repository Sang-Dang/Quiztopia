package com.fpt.assignment.controller.action;

import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.runtime.BackendException;
import com.fpt.assignment.service.UserService;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // get current requesting URL
        String currentURL = request.getHeader("referer");
        currentURL = Util.removeSuccessAndError(currentURL);
        String success, error;
        // get session
        HttpSession session = request.getSession(true);
        try {
            UUID userId = UserService.login(username, password);
            if(userId == null) {
                throw new BackendException();
            }
            session.setAttribute("currentUserId", userId);
            
            success = "Login successful. Have a great time!";
            response.sendRedirect("home?success=" + Util.encode(success));
            return;
        } catch (ValidationException ex) {
            ex.printStackTrace();
            error = "Username or password are in the wrong format. Please try again";
        } catch (ObjectNotFoundException ex) {
            ex.printStackTrace();
            error = "Username or password not found. Please try again";
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
