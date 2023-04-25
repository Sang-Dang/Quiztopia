package com.fpt.assignment.controller.page;

import com.fpt.assignment.dto.Result;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.service.ResultService;
import com.fpt.assignment.util.Util;
import java.io.IOException;
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
@WebServlet(name = "ViewResultsServlet", urlPatterns = {"/ViewResultsServlet"})
public class ViewResultsServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        UUID userId = (UUID) session.getAttribute("currentUserId");
        try {
            List<Result> results = ResultService.getResultsByUserId(userId.toString());
            if(results != null) {
                request.setAttribute("results", results);
            }
        } catch (UUIDParseException ex) {
            error = "Invalid token";
            ex.printStackTrace();
            response.sendRedirect("home?error=" + Util.encode(error));
            return;
        }
        request.getRequestDispatcher("WEB-INF/jsp/user-only/view-results.jsp").forward(request, response);
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
