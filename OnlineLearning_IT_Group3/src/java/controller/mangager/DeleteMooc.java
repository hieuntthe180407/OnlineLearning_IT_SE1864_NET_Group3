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
import model.Mooc;

/**
 *
 * @author DTC
 */
@WebServlet(name="DeleteMooc", urlPatterns={"/deleteMooc"})

public class DeleteMooc extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteMooc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteMooc at " + request.getContextPath() + "</h1>");
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
        MoocDAO ld = new MoocDAO();
        String moocid = request.getParameter("moocid");
        String courseid = request.getParameter("courseid");
        String moocnumber = request.getParameter("moocnumber");
        ld.deleteMooc(moocid, courseid);
        MoocDAO md = new MoocDAO();
        int moocid1 = -1;
        if (moocnumber.equals("1")) {
            moocid1 = md.GetIdMooc(1 ,  Integer.parseInt(courseid));
            if (moocid1 == -1) {
                String msg = "mooc does not exist";
                request.setAttribute("msg", msg);
            } else {
                MoocDAO md1 = new MoocDAO();
                Mooc m = md1.selectById(moocid1);
                request.setAttribute("mooc", m);
            }
        } else {
            int moocid2 = md.GetIdMooc((Integer.parseInt(moocnumber) - 1), Integer.parseInt(courseid));
            MoocDAO md1 = new MoocDAO();
            Mooc m = md1.selectById(moocid2);
            request.setAttribute("mooc", m);
        }

        request.setAttribute("courseid", Integer.parseInt(courseid));
        request.getRequestDispatcher("mooc?courseID="+courseid).forward(request, response);

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
