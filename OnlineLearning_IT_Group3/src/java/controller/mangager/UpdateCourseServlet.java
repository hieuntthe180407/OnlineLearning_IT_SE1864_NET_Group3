/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.mangager;

import dal.CategoryDAO;
import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Category;
import model.Course;

/**
 *
 * @author DTC
 */
@WebServlet(name = "UpdateCourseServlet", urlPatterns = {"/updateCourse"})
@MultipartConfig
public class UpdateCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID khóa học cần cập nhật
        int courseId = Integer.parseInt(request.getParameter("courseID"));

        // Lấy thông tin khóa học và danh sách danh mục từ cơ sở dữ liệu
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseByID(courseId);
        
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategory = categoryDAO.getAllCategory();
        
        // Gửi dữ liệu đến JSP
        request.setAttribute("course", course);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        
        // Xử lý ảnh tải lên
        Part courseImagePart = request.getPart("courseImg");
        String fileName = courseImagePart.getSubmittedFileName();
        
        // Nếu người dùng tải ảnh mới lên, lưu ảnh vào thư mục và lấy đường dẫn mới
        String imagePath = null;
        if (fileName != null && !fileName.isEmpty()) {
            imagePath = "images/" + fileName;
            courseImagePart.write(getServletContext().getRealPath("") + "images/" + fileName);
        }
        
        // Cập nhật khóa học trong cơ sở dữ liệu
        CourseDAO courseDAO = new CourseDAO();
        boolean isUpdated = courseDAO.updateCourse1(courseId, categoryId, courseName, description, imagePath);

        if (isUpdated) {
            response.sendRedirect("managerCourse"); // Redirect đến trang quản lý khóa học
        } else {
            request.setAttribute("error", "Failed to update the course.");
            request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
        }
    }
}
