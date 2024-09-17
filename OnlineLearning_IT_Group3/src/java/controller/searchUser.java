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
import java.util.Map;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name="searchUser", urlPatterns={"/searchUser"})
public class searchUser extends HttpServlet {
   
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String info = request.getParameter("query");
        UserDAO uDAO = new UserDAO();
        Map<Integer, User> list = uDAO.searchUser(info);
         request.setAttribute("list", list);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    } 

    
}
