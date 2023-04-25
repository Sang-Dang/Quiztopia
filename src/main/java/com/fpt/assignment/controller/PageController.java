package com.fpt.assignment.controller;

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
@WebServlet(name = "PageController", urlPatterns = {"/PageController", "/home"})
public class PageController extends HttpServlet {

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
        String page = request.getParameter("page");
        page = (page == null) ? "" : page;
        String url, base = "WEB-INF/jsp/", extension = ".jsp";
        switch (page) {
            case "login": {
                url = "login";
                break;
            }
            case "preview-quiz": {
                url = "PreviewQuizServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            case "manage-quizzes": {
                url = "ViewManageQuizServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            case "add-quiz": {
                url = "user-only/add-quiz";
                break;
            }
            case "view-quiz": {
                url = "ViewQuizServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            case "do-quiz": {
                url = "DoQuizServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            case "view-results": {
                url = "ViewResultsServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            default: {
                url = "user-only/home";
                break;
            }
        }
        request.getRequestDispatcher(base + url + extension).forward(request, response);
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
