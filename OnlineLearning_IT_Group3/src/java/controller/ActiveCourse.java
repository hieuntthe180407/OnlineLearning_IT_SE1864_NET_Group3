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
import model.Course;

/**
 *
 * @author trong
 */
@WebServlet(name="ActiveCourse", urlPatterns={"/ActiveCourse"})
public class ActiveCourse extends HttpServlet {
   
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       CourseDAO cDao = new CourseDAO();
        
        int page = 1;
        // lay so luong user moi page
        int itemsPerPage = request.getParameter("itemsPerPage")!= null 
                   ? Integer.parseInt(request.getParameter("itemsPerPage")) 
                   : 10;
         String search = request.getParameter("query")!= null 
                   ? request.getParameter("query")
                   : "";
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// Get total number of users
        int totalRecords = cDao.getTotalCourseCount(search);

// Fetch users for the current page
        List<Course> list = cDao.getCourseActive((page - 1) * itemsPerPage, itemsPerPage,search);

// Calculate the number of total pages
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / itemsPerPage);

// Set attributes 
        request.setAttribute("list", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.getRequestDispatcher("ActiveCourse.jsp").forward(request, response);
    } 

   
   
    
}
