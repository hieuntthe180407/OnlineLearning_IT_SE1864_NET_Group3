/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DTC
 */
@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

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
            out.println("<title>Servlet LogoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogoutController at " + request.getContextPath() + "</h1>");
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
        // Lấy session hiện tại nếu tồn tại, nếu không có session thì trả về null
        HttpSession session = request.getSession(false);

        // Nếu session khác null (tức là có session đang tồn tại)
        if (session != null) {
            // Hủy session để đăng xuất người dùng, xóa mọi thông tin trong session
            session.invalidate();
        }

        // Tạo một cookie mới tên là "email" với giá trị rỗng
        Cookie cuser = new Cookie("email", "");
        // Đặt thời gian sống của cookie là 0 để xóa cookie này ngay lập tức
        cuser.setMaxAge(0);
        // Thêm cookie vào phản hồi để trình duyệt xóa cookie "email"
        response.addCookie(cuser);

        // Tạo một cookie mới tên là "pass" với giá trị rỗng
        Cookie puser = new Cookie("pass", "");
        // Đặt thời gian sống của cookie là 0 để xóa cookie này ngay lập tức
        puser.setMaxAge(0);
        // Thêm cookie vào phản hồi để trình duyệt xóa cookie "pass"
        response.addCookie(puser);

        // Chuyển hướng người dùng đến trang đăng nhập ("/login")
        // `request.getContextPath()` tự động lấy đường dẫn gốc của ứng dụng
        response.sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
