/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.Category;
import model.User;

/**
 *
 * @author DTC
 */
@WebServlet(name = "AddCourseServlet", urlPatterns = {"/addCourse"})
@MultipartConfig
public class AddCourseServlet extends HttpServlet {

    private static final String DATA_DIRECTORY = "imageStorage\\course"; // Cập nhật đường dẫn

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategory = categoryDAO.getAllCategory();
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("addCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("teacher");

        // Kiểm tra xem người dùng có đăng nhập chưa
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CourseDAO courseDAO = new CourseDAO();

        // Lấy thông tin từ form
        int userId = user.getUserID();  // Sử dụng ID người dùng từ session
        int categoryId = Integer.parseInt(request.getParameter("category"));  // ID danh mục
        String courseName = request.getParameter("courseName");  // Tên khóa học
        String description = request.getParameter("description");  // Mô tả khóa học

        // Kiểm tra xem các thông tin có hợp lệ không
        if (courseName == null || courseName.isEmpty() || description == null || description.isEmpty()) {
            request.setAttribute("error", "Course name and description cannot be empty.");
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);
            return;
        }

        Part part = request.getPart("courseImg");  // Nhận ảnh khóa học từ form
        String fileName = getFileName(part);  // Lấy tên file ảnh

        if (fileName != null) {
            // Lưu ảnh vào thư mục
            String path = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();  // Tạo thư mục nếu chưa tồn tại
            }

            // Đường dẫn đầy đủ của file
            File file = new File(path + File.separator + fileName);
            part.write(file.getAbsolutePath());  // Lưu file vào thư mục

            // Lưu thông tin khóa học vào cơ sở dữ liệu
            boolean isInserted = courseDAO.insertCourse(userId, categoryId, DATA_DIRECTORY + File.separator + fileName, courseName, description);
            if (isInserted) {
                response.sendRedirect("managerCourse");  // Chuyển hướng đến trang quản lý khóa học
            } else {
                // Nếu có lỗi trong việc thêm khóa học vào cơ sở dữ liệu
                request.setAttribute("error", "Error inserting course.");
                request.getRequestDispatcher("addCourse.jsp").forward(request, response);  // Quay lại trang thêm khóa học
            }
        } else {
            // Nếu không có ảnh được tải lên
            request.setAttribute("error", "Course image is required.");
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);  // Quay lại trang thêm khóa học
        }
    }

// Phương thức lấy tên file từ đối tượng Part
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");  // Trả về tên file
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Add Course Servlet";
    }
}
