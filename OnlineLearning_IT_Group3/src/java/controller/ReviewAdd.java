/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet(name="ReviewAdd", urlPatterns={"/ReviewAdd"})
public class ReviewAdd extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int cID = Integer.parseInt(request.getParameter("CourseID"));
        String err="";
        try{
            
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        
       
        
             int uID = user.getUserID();
        String content = request.getParameter("ReviewContent");
        int rating = Integer.parseInt(request.getParameter("rating"));
        ReviewDAO rDAO = new ReviewDAO();
        
        rDAO.addReview(cID, content, uID,rating);
        response.sendRedirect("courseDetail?courseID="+cID);
        
        
        }
        catch(Exception e)
        {
             err= "You have to login first to review a course";
            response.sendRedirect("courseDetail?courseID="+cID+"&err="+err);
        }
    } 

   

}
