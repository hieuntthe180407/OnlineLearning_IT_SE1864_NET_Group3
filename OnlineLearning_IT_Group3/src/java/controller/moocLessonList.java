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
@WebServlet(name="moocLessonList", urlPatterns={"/moocLessonList"})
public class moocLessonList extends HttpServlet {
   
    

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int courseID = Integer.parseInt(request.getParameter("courseID"));
          MoocDAO m = new MoocDAO();
        List<Mooc> listm = m.getAllMoocByCourseID(courseID);
        request.setAttribute("listm", listm);
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
        
          LessonDAO l = new LessonDAO();
        List<Lesson> listl = l.getAlllessonBycourseID(courseID);
        request.setAttribute("listl", listl);
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
    } 

   
}
