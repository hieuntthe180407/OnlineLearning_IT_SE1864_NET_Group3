/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.LessonDAO;
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
@WebServlet(name="editStatusLesson", urlPatterns={"/editStatusLesson"})
public class editStatusLesson extends HttpServlet {
  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("LessonID"));
        int cid =Integer.parseInt(request.getParameter("courseID"));
        LessonDAO lDAO = new LessonDAO();
        
        if(status.equals("Active"))
        {
            lDAO.activeLessonStatus(id, status);
        }
        else if(status.equals("Disabled"))
        {
             lDAO.deactiveLessonStatus(id, status);
        }
        
       response.sendRedirect("courseDetail?courseID=" + cid);
        
    } 

    
}
