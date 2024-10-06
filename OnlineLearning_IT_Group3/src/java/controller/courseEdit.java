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
import java.util.List;
import model.Category;
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
        CategoryDAO caDAO= new CategoryDAO();
        List<Category> listCa = caDAO.getAllCategory();
        CourseDAO cDAO = new CourseDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
        
        Course c = cDAO.getCourseByID(id);
       
        request.setAttribute("listCa", listCa);
         request.setAttribute("Course", c);
                request.getRequestDispatcher("courseEdit.jsp").forward(request, response);
        
        }
        else{
           
        request.setAttribute("listCa", listCa);
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
        String idParam = request.getParameter("CourseID");
        String name = request.getParameter("courseName");
        int CategoryID = Integer.parseInt(request.getParameter("CategoryID"));
        String des = request.getParameter("description");
                CourseDAO c = new CourseDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
            c.updateCourse(id, CategoryID, name, des);
             response.sendRedirect("courseList");    
        }
        else{
            
            c.addCourse(name, CategoryID, des);
           response.sendRedirect("courseList");    
        }
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.sendRedirect("lessonEdit");        
        }
    }
    

}
