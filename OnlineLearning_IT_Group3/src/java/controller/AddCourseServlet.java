/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import model.User;

/**
 *
 * @author DTC
 */
@WebServlet(name = "AddCourseServlet", urlPatterns = {"/addCourse"})
public class AddCourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCourseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private static final String UPLOAD_DIR = "uploads"; // Thư mục lưu trữ ảnh

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String courseName = request.getParameter("courseName");
            int duration = Integer.parseInt(request.getParameter("duration"));
            int report = Integer.parseInt(request.getParameter("report"));
            String description = request.getParameter("description");
            double listPrice = Double.parseDouble(request.getParameter("price"));
            double salePrice = Double.parseDouble(request.getParameter("salePrice"));
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

            // Xử lý file ảnh
            Part filePart = request.getPart("courseImg");
            String fileName = filePart.getSubmittedFileName();
            String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR + File.separator + fileName;

            // Lưu ảnh vào thư mục uploads
            File uploadsDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdir(); // Tạo thư mục nếu không tồn tại
            }
            filePart.write(filePath);

            // Thêm khóa học vào cơ sở dữ liệu
            CourseDAO courseDAO = new CourseDAO();
            User currentUser = (User) request.getSession().getAttribute("currentUser"); // Giả định có user đang đăng nhập
            if (currentUser == null) {
                throw new Exception("User is not logged in");
            }

            boolean isAdded = courseDAO.addCourse(courseName, duration, report, fileName, description, listPrice, salePrice, isActive, currentUser);

            if (isAdded) {
                response.sendRedirect("courseList.jsp"); // Chuyển hướng đến danh sách khóa học
            } else {
                request.setAttribute("errorMessage", "Thêm khóa học không thành công. Vui lòng thử lại.");
                request.getRequestDispatcher("addCourse.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace(); // In thông tin lỗi ra console
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addCourse.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
