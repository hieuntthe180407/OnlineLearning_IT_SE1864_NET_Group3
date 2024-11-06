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
        //lessonID khac null
        if(idParam !=null){
            int id = Integer.parseInt(idParam);
        // lay toan bo thong tin lesson theo lessonID
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
        String err="";
        
        int cID = Integer.parseInt(request.getParameter("CourseID"));
        
    String idParam = request.getParameter("LessonID");
       
      
        LessonDAO l = new LessonDAO();
        // neu co lessonID thi se nhay vao update page
        if(idParam !=null){
            try{
        String name = request.getParameter("lessonName");
        String url = request.getParameter("lessonUrl");
        String des = request.getParameter("description");
        int num = Integer.parseInt(request.getParameter("LessonNumber"));
        if (name == null || name.trim().isEmpty() ||
        url == null || url.trim().isEmpty() ||
        des == null || des.trim().isEmpty())
                 {

        throw new IllegalArgumentException();
    }
        
            int id = Integer.parseInt(idParam);
            l.updateLesson(id, name, url,des,num);
            err="Lesson updated successfully";
             response.sendRedirect("courseDetail?courseID="+cID+ "&err="+err); 
            }
            // neu name,url,description,number null thi se catch
            catch(Exception e)
                    {
                        err="You must fill all the blank!";
                        response.sendRedirect("lessonEdit?LessonID="+idParam +"&CourseID="+cID+"&err="+err);
                    }
        }
        //lessonID = null thi nhay vao trang add
        else{
            try{
                String name = request.getParameter("lessonName");
        String url = request.getParameter("lessonUrl");
        String des = request.getParameter("description");
        int num = Integer.parseInt(request.getParameter("LessonNumber"));
        if (name == null || name.trim().isEmpty() ||
        url == null || url.trim().isEmpty() ||
        des == null || des.trim().isEmpty())
                 {

        throw new IllegalArgumentException();
    }
            
            l.addLesson(name, url,cID,des,num);
            err="Lesson added successfully";
           response.sendRedirect("courseDetail?courseID="+ cID+ "&err="+err);    
            }
            // neu name,url,description,number null thi se catch
            catch(Exception e)
                    {
                        err="You must fill all the blank!";
                        response.sendRedirect("lessonEdit.jsp?CourseID="+cID+"&err="+err);
                    }
        }
        
    }
    

   
}
