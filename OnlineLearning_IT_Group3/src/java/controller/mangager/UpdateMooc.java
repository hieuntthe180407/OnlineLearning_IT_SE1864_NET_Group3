/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.mangager;

import dal.MoocDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DTC
 */
@WebServlet(name = "UpdateMooc", urlPatterns = {"/updateMooc"})
public class UpdateMooc extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateMooc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMooc at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        int moocid = Integer.parseInt(request.getParameter("moocid"));
        int courseid = Integer.parseInt(request.getParameter("courseid"));
        int moocnumber = Integer.parseInt(request.getParameter("moocnumber"));
        String name = request.getParameter("name");

        request.setAttribute("moocid", moocid);
        request.setAttribute("courseid", courseid);
        request.setAttribute("lastnumber", moocnumber);
        request.setAttribute("name", name);

        request.getRequestDispatcher("updateMooc.jsp").forward(request, response);
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
        String courseId = request.getParameter("courseid");
        String moocNumber = request.getParameter("moocnumber");
        String moocName = request.getParameter("moocname");
        String moocid = request.getParameter("moocid");

        MoocDAO mdao = new MoocDAO();
        boolean update = mdao.updateMooc(moocid, moocName);

        response.sendRedirect("mooc?courseID=" + courseId);

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

