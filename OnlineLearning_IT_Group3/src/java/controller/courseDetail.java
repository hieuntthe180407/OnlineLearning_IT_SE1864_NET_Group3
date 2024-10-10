/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.MoocDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;
import model.Lesson;
import model.Mooc;

/**
 *
 * @author trong
 */
@WebServlet(name="courseDetail", urlPatterns={"/courseDetail"})
public class courseDetail extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
        int courseID = Integer.parseInt(request.getParameter("courseID"));
          MoocDAO m = new MoocDAO();
        List<Mooc> listm = m.getAllMoocByCourseID(courseID);
        request.setAttribute("listm", listm);
        
        
          LessonDAO l = new LessonDAO();
       
        
         int page = 1;
        int recordsPerPage =3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// Get total number of users
        int totalRecords = l.getTotalLessonCount(courseID);

// Fetch users for the current page
        List<Lesson> users = l.getLessons((page - 1) * recordsPerPage, recordsPerPage,courseID);

// Calculate the number of total pages
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

// Set attributes for pagination
        request.setAttribute("listl", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        
        
        
        CourseDAO cDAO = new CourseDAO();
        Course c = cDAO.getCourseByID(courseID);
        request.setAttribute("Course", c);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.sendRedirect("courseDetail");        
        }
        
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
    } 

}
