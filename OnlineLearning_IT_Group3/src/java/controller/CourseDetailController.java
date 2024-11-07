/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.LessonDAO;
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

/**
 *
 * @author HP
 */
@WebServlet(name = "CourseDetailController", urlPatterns = {"/courseDetailUserView"})
public class CourseDetailController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            CourseDAO courseDAO = new CourseDAO();
            LessonDAO lessonDao = new LessonDAO();
            Course course = courseDAO.getCourseByID(courseId);
            List<Lesson> lessons = lessonDao.getAlllessonBycourseID(courseId);
            request.setAttribute("course", course);
            request.setAttribute("lessons", lessons);

            request.getRequestDispatcher("courseDetailsView.jsp").forward(request, response);
        }catch(Exception e) {
            System.out.println("Error: "+ e);
        }
    }
}
