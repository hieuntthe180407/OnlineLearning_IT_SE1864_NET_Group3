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
        String idParam = request.getParameter("LessonID");
        LessonDAO lDAO = new LessonDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
        
        Lesson l = lDAO.getLessonByID(id);
        
         request.setAttribute("lesson", l);
                request.getRequestDispatcher("lessonEdit.jsp").forward(request, response);
        
        }
        else{
             request.getRequestDispatcher("lessonEdit.jsp").forward(request, response);
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
        LessonDAO l = new LessonDAO();
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
            l.updateLesson(id, name, url,des,num);
             response.sendRedirect("courseList");    
        }
        else{
            int cID = Integer.parseInt(request.getParameter("CourseID"));
            l.addLesson(name, url,cID,des,num);
           response.sendRedirect("courseDetail?CourseID="+ cID);    
        }
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.sendRedirect("lessonEdit");        
        }
    }
    

   
}
