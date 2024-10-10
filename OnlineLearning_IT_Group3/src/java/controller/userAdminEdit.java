/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
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
@WebServlet(name="userAdminEdit", urlPatterns={"/userAdminEdit"})
public class userAdminEdit extends HttpServlet {
   
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       int role = Integer.parseInt(request.getParameter("role"));
       String status = request.getParameter("status");
       int id = Integer.parseInt(request.getParameter("userID"));
       String noti;
       UserDAO u = new UserDAO();
       
       try{
       u.updateUserRoleStatus(id, role, status);
       noti = "Update successfully";
       }
       catch (Exception e){
          noti = "There are some errors";
       }
       request.setAttribute("noti", noti);
       request.getRequestDispatcher("userDetail").forward(request, response);
    } 

}
