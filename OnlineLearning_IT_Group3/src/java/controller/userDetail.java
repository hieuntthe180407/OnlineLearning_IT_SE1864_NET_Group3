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
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name="userDetail", urlPatterns={"/userDetail"})
public class userDetail extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        UserDAO uDAO = new UserDAO();
        User u =uDAO.getUserProfilebyId(userId);
        // lay thong tin cua user theo userID
        request.setAttribute("user", u);
        request.getRequestDispatcher("userDetailEdit.jsp").forward(request, response);
        
    } 

    

}
