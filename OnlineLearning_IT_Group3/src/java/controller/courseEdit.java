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
import model.Course;

/**
 *
 * @author trong
 */
@WebServlet(name="courseEdit", urlPatterns={"/courseEdit"})
public class courseEdit extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
        String idParam = request.getParameter("courseID");
        CourseDAO cDAO = new CourseDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
        
        Course c = cDAO.getCourseByID(id);
        
         request.setAttribute("Course", c);
                request.getRequestDispatcher("courseEdit.jsp").forward(request, response);
        
        }
        else{
             request.getRequestDispatcher("courseEdit.jsp").forward(request, response);
        }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            
            response.sendRedirect("courseDetail");
        }
        
    } 
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
        String idParam = request.getParameter("LessonID");
        String name = request.getParameter("lessonName");
        String url = request.getParameter("lessonUrl");
        String des = request.getParameter("description");
        int num = Integer.parseInt(request.getParameter("LessonNumber"));
        CourseDAO l = new CourseDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
            l.update(id, name, url,des,num);
             response.sendRedirect("courseList");    
        }
        else{
            int moocID = Integer.parseInt(request.getParameter("MoocID"));
            l.addLesson(name, url,moocID,des,num);
           response.sendRedirect("courseList");    
        }
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.sendRedirect("lessonEdit");        
        }
    }
    

}
