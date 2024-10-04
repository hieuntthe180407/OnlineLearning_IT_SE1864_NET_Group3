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
import java.util.List;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name="filterUser", urlPatterns={"/filterUser"})
public class filterUser extends HttpServlet {
   
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
       UserDAO uDao = new UserDAO();
         int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// Get total number of users
        int totalRecords = uDao.getTotalUserFilterCount(gender,role,status);
        
        
        List<User> users = uDao.filterUser(gender,role,status,(page - 1) * recordsPerPage, recordsPerPage);
        
         int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

// Set attributes for pagination
        request.setAttribute("users", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    } 

   

}