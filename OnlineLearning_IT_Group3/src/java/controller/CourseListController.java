/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Course;

/**
 *
 * @author DTC
 */
@WebServlet(name="CourseListController", urlPatterns={"/courseList"})
public class CourseListController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CourseListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseListController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         CourseDAO cDao = new CourseDAO();
        List<Category> listTop8Category = cDao.getTop8Category();
        request.setAttribute("listTop8Category", listTop8Category);

        List<Course> listAllCourse = null;
        //Lấy tham số action từ yêu cầu của người dùng (URL hoặc form) để xác định hành động mà người dùng muốn thực hiện.
        String action = request.getParameter("action");

        // Check if action is null or empty
        if (action == null || action.isEmpty()) {

            listAllCourse = cDao.getAllCourse();

        } else if (action.equalsIgnoreCase("search")) {
            // nếu hành động là search thì sẽ lấy ra text của nó và search course đó theo text đó
            String text = request.getParameter("text");
            listAllCourse = cDao.getAllCourseBySearch(text);
        } else if (action.equalsIgnoreCase("category")) {
              // nếu hành động là tìm kiếm theo category thì sẽ lấy ra giá trị của nó và tìm kiếm course đó theo text đó
            String name = request.getParameter("name");
            listAllCourse = cDao.getAllCoursesByCategory(name);
        } else if (action.equalsIgnoreCase("filterPrice")) {
           double minPrice = Double.parseDouble(request.getParameter("minPrice"));
           double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
           listAllCourse = cDao.getCourseByMinMaxPrice(minPrice,maxPrice);

        }

        double maxPrice = cDao.getMaxPrice();
        request.setAttribute("maxPrice", maxPrice);
        double minPrice = cDao.getMinPrice();
        request.setAttribute("minPrice", minPrice);

        request.setAttribute("listCourse", listAllCourse);
        request.getRequestDispatcher("/courseList.jsp").forward(request, response);
    
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
