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

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CourseDAO courseDAO = new CourseDAO();

        int userId = 1;  // Có thể thay thế bằng user.getUserID()
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");

        Part part = request.getPart("courseImg");
        String fileName = getFileName(part);

        if (fileName != null) {
            // Lưu ảnh vào thư mục
            String path = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Đường dẫn đầy đủ của file
            File file = new File(path + File.separator + fileName);
            part.write(file.getAbsolutePath());

            // Lưu đường dẫn vào cơ sở dữ liệu
            boolean isInserted = courseDAO.insertCourse(userId, categoryId, DATA_DIRECTORY + File.separator + fileName, courseName, description);
            if (isInserted) {
                response.sendRedirect("managerCourse");
            } else {
                request.setAttribute("error", "Error inserting course.");
                request.getRequestDispatcher("addCourse.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Course image is required.");
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Add Course Servlet";
    }
}