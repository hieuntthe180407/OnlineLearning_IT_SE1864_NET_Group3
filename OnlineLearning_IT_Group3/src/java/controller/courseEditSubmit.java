/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name = "courseEditSubmit", urlPatterns = {"/courseEditSubmit"})
public class courseEditSubmit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("acc");
        String idParam = request.getParameter("CourseID");
        String noti;
        CourseDAO c = new CourseDAO();
        CategoryDAO caDAO= new CategoryDAO();
        List<Category> listCa = caDAO.getAllCategory();
        try {
            String name = request.getParameter("courseName");
            int CategoryID = Integer.parseInt(request.getParameter("category"));
            String des = request.getParameter("description");
            if (name == null || name.trim().isEmpty() || des == null || des.trim().isEmpty() || CategoryID == 100) {
                noti = "Course name, Category or description cannot be empty.";
            } else {
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    c.updateCourse(id, CategoryID, name, des, u.getUserID());
                    noti = "Update successfully";

                } else {
                    String img = request.getParameter("image");
                    c.addCourse(name, CategoryID, des, img, u.getUserID());
                    noti = "Added successfully";
                }
            }
        } catch (Exception e) {

            noti = "There are some errors";
        }
        request.setAttribute("noti", noti);
        request.setAttribute("listCa", listCa);
        request.getRequestDispatcher("courseEdit.jsp").forward(request, response);
    }
}
