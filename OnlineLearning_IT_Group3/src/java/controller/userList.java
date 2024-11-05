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
import java.util.*;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name = "userList", urlPatterns = {"/userList"})
public class userList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDao = new UserDAO();
        
        int page = 1;
        // lay so luong user moi page
        int itemsPerPage = request.getParameter("itemsPerPage")!= null 
                   ? Integer.parseInt(request.getParameter("itemsPerPage")) 
                   : 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// Get total number of users
        int totalRecords = uDao.getTotalUserCount();

// Fetch users for the current page
        List<User> users = uDao.getUsers((page - 1) * itemsPerPage, itemsPerPage);

// Calculate the number of total pages
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / itemsPerPage);

// Set attributes 
        request.setAttribute("users", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

}
