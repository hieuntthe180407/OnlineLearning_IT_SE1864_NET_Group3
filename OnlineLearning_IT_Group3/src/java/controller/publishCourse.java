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

/**
 *
 * @author trong
 */
@WebServlet(name="publishCourse", urlPatterns={"/publishCourse"})
public class publishCourse extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String noti;
        try{
        int publish = Integer.parseInt(request.getParameter("publish"));
        int cid = Integer.parseInt(request.getParameter("courseID"));
        
        CourseDAO cDAO = new CourseDAO();
        cDAO.updateCoursePublish(cid, publish);
        noti = "Update successfully";
        }
        catch (Exception e)
        {
            noti = "There are some errors. Update failed";
        }
        request.setAttribute("noti", noti);
        request.getRequestDispatcher("ActiveCourse").forward(request, response);
   } 

    

}
