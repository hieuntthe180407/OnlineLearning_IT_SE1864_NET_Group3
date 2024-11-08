/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.SendMail;
import util.Token;

/**
 *
 * @author DTC
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgot"})
public class ForgotPassword extends HttpServlet {

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
            out.println("<title>Servlet ForgotPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
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
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        url += "/forgotPassword.jsp";

        request.getSession().removeAttribute("mess");

        // response.sendRedirect(url);
        request.getRequestDispatcher(url).forward(request, response);
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

        // Thiết lập URL cơ bản của ứng dụng, kết hợp với đường dẫn đến trang `forgotPassword.jsp`
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        url += "/forgotPassword.jsp";

        // Lấy địa chỉ email người dùng đã nhập vào từ form (thông qua phương thức POST)
        String email = request.getParameter("email");

        // Kiểm tra xem email có khác null không (tức là người dùng đã nhập email)
        if (email != null) {
            // Tạo một đối tượng UserDAO để thao tác với dữ liệu người dùng
            UserDAO userDao = new UserDAO();

            // Gọi phương thức `checkMailRegister` để kiểm tra xem email đã đăng ký trong hệ thống chưa
            boolean checkMail = userDao.checkMailRegister(email);

            // Nếu email đã đăng ký, tiến hành gửi thông báo đặt lại mật khẩu
            if (checkMail) {
                // Ghi "true" vào đối tượng response (phản hồi lại cho phía client)
                response.getWriter().write(String.valueOf(true));

                // Tạo một mã thông báo (token) bảo mật để xác minh khi người dùng đặt lại mật khẩu
                String token = Token.generateToken();

                // Gửi email đến người dùng kèm theo mã token để đặt lại mật khẩu
                SendMail.sendMail(email, token);

                // Đặt thông báo vào session, yêu cầu người dùng kiểm tra email để thay đổi mật khẩu
                request.getSession().setAttribute("mess", "Please check your email and change password");

                // Chuyển hướng đến trang forgotPassword.jsp
                response.sendRedirect(url);
            } else {
                // Nếu email chưa đăng ký, ghi "false" vào response để phản hồi phía client
                response.getWriter().write(String.valueOf(false));

                // Đặt thông báo lỗi vào session để hiển thị thông báo thất bại
                request.getSession().setAttribute("mess", "Password update failed. Please try again.");

                // Chuyển hướng đến trang forgotPassword.jsp để thông báo lỗi
                response.sendRedirect(url);
            }
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
