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
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import model.User;
import util.SendMailRegister;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "verifyServlet", urlPatterns = {"/verifyServlet"})
public class verifyServlet extends HttpServlet {

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
            out.println("<title>Servlet verifyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet verifyServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        //Lấy session của tempUser
        User user = (User) session.getAttribute("tempInfoUser");
        //Tạo xác thực ngẫu nhiên
        String code = getRandomNumberString();
        //Cho mã xác thực vào session
        session.setAttribute("verificationCode", code);
        //Gửi email cho user
        SendMailRegister.sendMailRegister(user.getEmail(), code);
        request.getRequestDispatcher("verifyEmail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String enteredCode = request.getParameter("verificationCode");
        String generatedCode = (String) session.getAttribute("verificationCode");

        // Kiểm tra mã xác thực
        if (enteredCode.equals(generatedCode)) {
            User user = (User) session.getAttribute("tempInfoUser");
            UserDAO userDao = new UserDAO();
            userDao.insertNewUserStudent(user);

            // Xóa dữ liệu tạm thời trong session
            session.removeAttribute("tempInfoUser");
            session.removeAttribute("verificationCode");

            // Chuyển hướng đến trang đăng nhập
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Nếu mã không đúng, thông báo lỗi và yêu cầu nhập lại
            request.setAttribute("errorCode", "Invalid verification code. Please try again.");
            request.getRequestDispatcher("verifyEmail.jsp").forward(request, response);
        }

    }

    public static String getRandomNumberString() {
        // Tạo mã số có 6 chữ số.
        // từ 0 tới 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
         // Chuyển đổi mã thành chuỗi có 6 chữ số
        return String.format("%06d", number);
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
