/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CourseDAO;
import dal.EnrollDAO;
import dal.LessonDAO;

import dal.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.Lesson;

import model.Review;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name="courseDetail", urlPatterns={"/courseDetail"})
public class courseDetail extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         int courseID = Integer.parseInt(request.getParameter("courseID"));
         HttpSession session = request.getSession();
         User u = (User)session.getAttribute("acc");
         
        try{
        String display = request.getParameter("displayOption")!= null 
                   ? request.getParameter("displayOption") 
                   : "both";
            
      // get all reivews
       ReviewDAO rDAO = new ReviewDAO();
       List<Review> listr = rDAO.getReviewByCourseId(courseID);
       
       
       
       request.setAttribute("listr", listr);
      
          LessonDAO l = new LessonDAO();
       
        
         int page = 1;
         // lay so luong item trong 1 trang tu trang jsp, mac dinh se duoc set la 10 items
        int itemsPerPage = request.getParameter("itemsPerPage")!= null 
                   ? Integer.parseInt(request.getParameter("itemsPerPage")) 
                   : 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// Get total number of users
        int totalRecords = l.getTotalLessonCount(courseID);

// Fetch users for the current page
        List<Lesson> users = l.getLessons((page - 1) * itemsPerPage, itemsPerPage,courseID);

// Calculate the number of total pages
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / itemsPerPage);

// Set attributes for pagination
        request.setAttribute("listl", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("displayOption", display);
        // Get all the Course info includes (about teacher, price, description)
        CourseDAO cDAO = new CourseDAO();
        Course c = cDAO.getCourseTeacherByID(courseID);
        request.setAttribute("Course", c);
        }
        catch (Exception e){
            
            response.sendRedirect("courseDetail");        
        }
        if(u==null)
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
        else{
            if(u.getRole().getRoleId()==2)
            {
                request.getRequestDispatcher("courseDetailTeacher.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
            }
        }
        
       
    }
}
