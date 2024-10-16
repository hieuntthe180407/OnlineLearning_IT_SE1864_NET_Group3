/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.CourseRegisDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.CourseRegistration;
import model.User;
/**
 *
 * @author laptop acer
 */

public class CourseRegisterController extends HttpServlet {

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
            out.println("<title>Servlet CourseRegisterController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseRegisterController at " + request.getContextPath() + "</h1>");
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
        //        Cookie[] cookies = request.getCookies();
        String email = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                // Kiểm tra xem cookie có tên là "email" không
//                if ("email".equals(cookie.getName())) {
//                    // Lấy giá trị của cookie email
//                    email = cookie.getValue();
//                    // Thực hiện hành động với giá trị email
//                    System.out.println("Email from cookie: " + email);
//                }
//            } }
////        String email = (String) request.getSession().getAttribute("email");
//        if (email == null) {
//            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
//            response.sendRedirect("login.jsp");
//            return;
//        }
        email = "a@example.com";
        CourseRegisDao courseRegisDao = new CourseRegisDao();
        List<CourseRegistration> registrations = courseRegisDao.getCourseRegistrationsByEmail(email);
        request.setAttribute("registrations", registrations);
        request.getRequestDispatcher("myCourseRegistration.jsp").forward(request, response);
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
        String courseId = request.getParameter("courseID");
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("acc");
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "login.jsp");
        }
        CourseRegisDao cd = new CourseRegisDao();
        cd.registCourse(u, courseId);
        CourseDAO cDao = new CourseDAO();
        List<Course> listAllCourse = cDao.getAllCourse();
        request.setAttribute("listCourse", listAllCourse);
        request.getRequestDispatcher("/courseList.jsp").forward(request, response);
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
