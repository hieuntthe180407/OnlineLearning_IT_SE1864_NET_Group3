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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.PasswordEncryption;

/**
 *
 * @author DTC
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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

        Cookie[] cookies = request.getCookies();// lấy tất cả cookie từ yêu cầu.
        //Khởi tạo các biến email, pass, và check để lưu giá trị cookie.
        String email = "";
        String pass = "";
        String check = null;

        //Vòng lặp kiểm tra từng cookie để lấy giá trị email, pass, và checked.
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("email".equals(cookie.getName())) {
                    email = cookie.getValue();
                }

                if ("pass".equals(cookie.getName())) {
                    email = cookie.getValue();
                }

                if ("checked".equals(cookie.getName())) {
                    email = cookie.getValue();
                }

            }
        }
        //Gán giá trị cho các thuộc tính 
        request.setAttribute("email", email);
        request.setAttribute("pass", pass);
        request.setAttribute("checked", check);

        //Lấy thông báo từ session và gán cho msg1, sau đó xóa msg1 khỏi session.
        HttpSession session = request.getSession();

        String msg1 = (String) session.getAttribute("msg1");
        request.setAttribute("msg1", msg1);
        session.removeAttribute("msg1");

        request.getSession().removeAttribute("mess");
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        // Thiết lập URL cơ bản cho ứng dụng và lấy session hiện tại với thời gian hết hạn là 10 phút (600 giây)
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600);

        // Lấy thông tin từ form đăng nhập (username, password, trạng thái "checked" nếu người dùng chọn "ghi nhớ đăng nhập")
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        String checked[] = request.getParameterValues("check");

        // Kiểm tra trạng thái "checked", nếu không có chọn thì gán biến `c` là "notchecked"
        String c = "checked";
        if (checked == null) {
            c = "notchecked";
        }

        // Tạo đối tượng UserDAO để thao tác với dữ liệu người dùng và lấy thông tin người dùng dựa vào email
        UserDAO ud = new UserDAO();
        User u = ud.getUserByEmail(email);

        // Tạo cookie cho email, password và trạng thái "checked"
        Cookie cuser = new Cookie("email", email);
        Cookie puser = new Cookie("pass", password);
        Cookie ruser = new Cookie("checked", c);
        ruser.setMaxAge(60 * 60 * 24 * 7); // Đặt cookie "checked" có thời gian tồn tại là 7 ngày
        response.addCookie(ruser);

        // Nếu không tìm thấy người dùng (u == null), đặt thông báo lỗi và chuyển tiếp về trang login.jsp
        if (u == null) {
            url += "/login";
            String failed = "Account is not registered!!! ";
            request.setAttribute("failed", failed);
            request.setAttribute("email", email);

            // Xóa cookie email và password vì tài khoản không tồn tại
            cuser.setMaxAge(0);
            puser.setMaxAge(0);

            // Chuyển hướng đến trang login.jsp với thông báo lỗi
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Nếu mật khẩu không khớp, đặt thông báo lỗi và chuyển tiếp về trang login.jsp
            if (!PasswordEncryption.verify(password, u.getPassword())) {
                url += "/login";
                String failed = "Password incorrect!";
                request.setAttribute("failed", failed);
                request.setAttribute("email", email);

                // Xóa cookie email và password vì mật khẩu không chính xác
                cuser.setMaxAge(0);
                puser.setMaxAge(0);
                request.setAttribute("failed", failed);

                // Chuyển hướng đến trang login.jsp với thông báo lỗi
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // Đặt session `acc` với thông tin người dùng `u` sau khi đăng nhập thành công
                session.setAttribute("acc", u);

                /* Nếu "checked" thì lưu cookie cho `cuser` và `puser` với thời gian sống là 7 ngày,
               nếu không thì xóa cookie để không lưu thông tin đăng nhập */
                if (!c.equals("notchecked")) {
                    cuser.setMaxAge(60 * 60 * 24 * 7);
                    puser.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cuser);
                    response.addCookie(puser);
                } else {
                    cuser.setMaxAge(0);
                    puser.setMaxAge(0);
                }

                // Kiểm tra quyền của người dùng (RoleId) và điều hướng đến trang phù hợp
                if (u.getRole().getRoleId() == 1) { // Người dùng là sinh viên
                    session.setAttribute("user", u);
                    url += "/home";
                } else if (u.getRole().getRoleId() == 2) { // Người dùng là giáo viên
                    session.setAttribute("teacher", u);
                    url += "/managerCourse";
                } else if (u.getRole().getRoleId() == 3) { // Người dùng là admin
                    session.setAttribute("admin", u);
                    url += "/Admin.jsp";
                } else {
                    // Nếu không xác định được quyền người dùng, ném ra ngoại lệ và ghi lại lỗi
                    try {
                        throw new Exception();
                    } catch (Exception ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Điều hướng đến URL tương ứng dựa trên quyền của người dùng
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
