/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CourseDAO;
import dal.EnrollDAO;
import dal.LessonDAO;
import dal.MoocDAO;

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
import model.Mooc;

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
            //lay phuong thuc hien thi ten va anh, neu null thi mac dinh la hien thi ca 2
        String display = request.getParameter("displayOption")!= null 
                   ? request.getParameter("displayOption") 
                   : "both";
            
      // get all reivews
       ReviewDAO rDAO = new ReviewDAO();
       List<Review> listr = rDAO.getReviewByCourseId(courseID);
        MoocDAO m = new MoocDAO();
        List<Mooc> listm = m.getAllMoocByCourseID(courseID);
       
       
       request.setAttribute("listr", listr);
      request.setAttribute("listm", listm);
          LessonDAO l = new LessonDAO();
       
        
         int page = 1;
         // lay so luong item trong 1 trang tu trang jsp, mac dinh se duoc set la 10 items
        int itemsPerPage = request.getParameter("itemsPerPage")!= null 
                   ? Integer.parseInt(request.getParameter("itemsPerPage")) 
                   : 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

// lay so luong lesson trong course
        int totalRecords = l.getTotalLessonCount(courseID);

// cai dat so luong lesson moi page
        List<Lesson> lessons = l.getLessons((page - 1) * itemsPerPage, itemsPerPage,courseID);

// Tinh so luong tong page se co
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / itemsPerPage);

// Set attributes 
        request.setAttribute("listl", lessons);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("displayOption", display);
        // Lay toan bo thong tin cua course theo courseID (about teacher, price, description)
        CourseDAO cDAO = new CourseDAO();
        Course c = cDAO.getCourseTeacherByID(courseID);
        request.setAttribute("Course", c);
        }
        catch (Exception e){
            
            response.sendRedirect("courseDetail");        
        }
        if(u==null)
            //Guest truy cap se vao trang courseDetail
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
        else{
            // nguoi dung da dang nhap 
            if(u.getRole().getRoleId()==2)
            {
                // neu la teacher thi se vao trang them, sua
                request.getRequestDispatcher("courseDetailTeacher.jsp").forward(request, response);
            }
            else
            {
                // neu la student thi se vao trang courseDetail nhu guest
                request.getRequestDispatcher("courseDetail.jsp").forward(request, response);
            }
        }
        
       
    }
}
