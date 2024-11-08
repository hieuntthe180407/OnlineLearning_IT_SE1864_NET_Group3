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
        // Lấy ID khóa học cần cập nhật từ URL
        int courseId = Integer.parseInt(request.getParameter("courseID"));

        // Tạo đối tượng CourseDAO để lấy thông tin khóa học từ cơ sở dữ liệu
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseByID(courseId);  // Lấy thông tin khóa học theo ID

        // Tạo đối tượng CategoryDAO để lấy danh sách danh mục từ cơ sở dữ liệu
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategory = categoryDAO.getAllCategory();  // Lấy tất cả danh mục khóa học

        // Gửi thông tin khóa học và danh sách danh mục đến JSP để hiển thị
        request.setAttribute("course", course);  // Dữ liệu khóa học
        request.setAttribute("listCategory", listCategory);  // Dữ liệu danh mục
        request.getRequestDispatcher("updateCourse.jsp").forward(request, response);  // Chuyển hướng đến trang updateCourse.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form gửi lên
        int courseId = Integer.parseInt(request.getParameter("courseId"));  // ID khóa học
        String courseName = request.getParameter("courseName");  // Tên khóa học
        String description = request.getParameter("description");  // Mô tả khóa học
        int categoryId = Integer.parseInt(request.getParameter("category"));  // ID danh mục của khóa học

        // Xử lý ảnh tải lên từ form
        Part courseImagePart = request.getPart("courseImg");  // Lấy đối tượng Part cho ảnh khóa học
        String fileName = courseImagePart.getSubmittedFileName();  // Lấy tên file ảnh

        // Kiểm tra nếu người dùng tải ảnh mới lên, lưu ảnh vào thư mục "images" trên server và lấy đường dẫn ảnh
        String imagePath = null;
        if (fileName != null && !fileName.isEmpty()) {
            imagePath = "images/" + fileName;  // Đường dẫn ảnh
            // Lưu ảnh vào thư mục images trong project
            courseImagePart.write(getServletContext().getRealPath("") + "images/" + fileName);
        }

        // Tạo đối tượng CourseDAO để thực hiện việc cập nhật thông tin khóa học trong cơ sở dữ liệu
        CourseDAO courseDAO = new CourseDAO();
        boolean isUpdated = courseDAO.updateCourse1(courseId, categoryId, courseName, description, imagePath);  // Cập nhật khóa học

        // Kiểm tra nếu việc cập nhật thành công
        if (isUpdated) {
            response.sendRedirect("managerCourse");  // Chuyển hướng đến trang quản lý khóa học
        } else {
            // Nếu cập nhật không thành công, gửi thông báo lỗi và quay lại trang updateCourse.jsp
            request.setAttribute("error", "Failed to update the course.");
            request.getRequestDispatcher("updateCourse.jsp").forward(request, response);  // Quay lại trang updateCourse.jsp
        }
    }
}
