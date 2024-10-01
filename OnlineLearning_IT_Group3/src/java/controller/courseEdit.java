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
    

}
