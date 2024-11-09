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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Course;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name = "courseEdit", urlPatterns = {"/courseEdit"})
public class courseEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy ID khóa học từ request
            String idParam = request.getParameter("courseID");
            CategoryDAO caDAO = new CategoryDAO();
            List<Category> listCa = caDAO.getAllCategory();  // Lấy danh sách các danh mục

            CourseDAO cDAO = new CourseDAO();
            if (idParam != null) {
                // Nếu có courseID, lấy thông tin chi tiết khóa học theo ID
                int id = Integer.parseInt(idParam);
                Course c = cDAO.getCourseByID(id);  // Lấy thông tin khóa học từ cơ sở dữ liệu

                // Gửi danh sách danh mục và thông tin khóa học đến JSP
                request.setAttribute("listCa", listCa);
                request.setAttribute("Course", c);
            } else {
                // Nếu không có courseID, chỉ gửi danh sách danh mục
                request.setAttribute("listCa", listCa);
            }
        } catch (Exception e) {
            // In ra lỗi nếu có ngoại lệ
            System.out.println(e.getMessage());
        }

        // Chuyển tiếp đến trang chỉnh sửa khóa học
        request.getRequestDispatcher("courseEdit.jsp").forward(request, response);
    }

}
