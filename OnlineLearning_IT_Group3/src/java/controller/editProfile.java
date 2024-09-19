/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        UserDAO u = new UserDAO();
        try {
            User profile = u.getUserProfilebyId(user.getUserID());
            request.setAttribute("profile", profile);
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        UserDAO u = new UserDAO();
        try {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String avatar = request.getParameter("avatar");
            String oldAvatar = request.getParameter("oldAvatar");

            //check is there are any new avatar
            if (avatar.isEmpty()) {
                avatar = oldAvatar;
            }

            User pf = u.getUserProfilebyId(user.getUserID());
            pf.setFullName(fullName);
            pf.setGender(gender);
            pf.setDateOfBirth(dateOfBirth);
            pf.setPhone(phone);
            pf.setAddress(address);
            pf.setAvatar(avatar);
            u.updateUserProfile(pf);
            response.sendRedirect("userProfile");
        } catch (Exception e) {
            System.out.println(e);

        }
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
