/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="BlogUserViewDetailController", urlPatterns={"/BlogUserViewDetailController"})
public class BlogUserViewDetailController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int blogId = Integer.parseInt(request.getParameter("blogId"));
            BlogDAO blogDAO = new BlogDAO();

            Blog blog = blogDAO.viewBlogDetail(blogId);
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("blogDetailViewUser.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
