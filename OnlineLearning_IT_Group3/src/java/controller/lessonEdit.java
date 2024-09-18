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
import model.Lesson;

/**
 *
 * @author trong
 */
@WebServlet(name="lessonEdit", urlPatterns={"/lessonEdit"})
public class lessonEdit extends HttpServlet {
   
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
        int id = Integer.parseInt(request.getParameter("LessonID"));
        LessonDAO lDAO = new LessonDAO();
        Lesson l = lDAO.getLessonByID(id);
         request.setAttribute("lesson", l);
                request.getRequestDispatcher("lessonEdit.jsp").forward(request, response);
        
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
        String name = request.getParameter("LessonName");
        String url = request.getParameter("LessonURL");
        LessonDAO l = new LessonDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
            l.updateLesson(id, name, url);
        }
        else{
            l.addLesson(name, url);
        }
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.sendRedirect("lessonEdit.jsp");        
        }
    }
    

   
}
