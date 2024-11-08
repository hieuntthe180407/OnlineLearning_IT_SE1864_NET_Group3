/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mangager;

import dal.CourseDAO;
import dal.MoocDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DTC
 */
@WebServlet(name = "CreateMooc", urlPatterns = {"/CreateMooc"})

public class CreateMooc extends HttpServlet {

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
            out.println("<title>Servlet CreateMooc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateMooc at " + request.getContextPath() + "</h1>");
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
        // Lấy tham số courseid từ URL hoặc form
        String courseidp = request.getParameter("courseid"); // Nhận giá trị courseid dưới dạng String
        int courseid = Integer.parseInt(courseidp); // Chuyển đổi courseid sang kiểu int

        // Tạo đối tượng CourseDAO để tương tác với cơ sở dữ liệu
        CourseDAO cd = new CourseDAO();

        // Lấy số lượng Mooc hiện tại cho courseid từ cơ sở dữ liệu
        int number = cd.getNumberMax(courseidp); // Sử dụng phương thức getNumberMax để lấy số lượng Mooc hiện tại

        // Gửi các tham số cần thiết đến trang JSP
        request.setAttribute("lastnumber", number + 1); // Thêm 1 vào số lượng để lấy số Mooc tiếp theo
        request.setAttribute("courseid", courseid); // Truyền giá trị courseid đến trang JSP

        // Chuyển hướng đến trang createMooc.jsp để người dùng có thể tạo Mooc mới
        request.getRequestDispatcher("createMooc.jsp").forward(request, response);
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
        // Lấy các tham số từ request (dữ liệu người dùng gửi)
        String moocnumber = request.getParameter("moocnumber");  // Số thứ tự của Mooc
        String moocname = request.getParameter("moocname");      // Tên Mooc
        String courseid = request.getParameter("courseid");      // Mã khóa học

        // Chuyển đổi các tham số từ chuỗi sang số nguyên (int)
        int number = Integer.parseInt(moocnumber);  // Chuyển moocnumber sang kiểu int
        int course = Integer.parseInt(courseid);    // Chuyển courseid sang kiểu int

        // Tạo đối tượng MoocDAO để thực hiện các thao tác với cơ sở dữ liệu
        MoocDAO md = new MoocDAO();

        // Thêm một Mooc mới vào cơ sở dữ liệu
        boolean c = md.addANewMooc(moocnumber, moocname, courseid);

        // Tạo đối tượng MoocDAO mới để lấy ID của Mooc vừa tạo
        MoocDAO md1 = new MoocDAO();
        int moocid = md1.GetIdMooc(number, course);  // Lấy ID của Mooc dựa vào số thứ tự và khóa học

        // Nếu việc thêm Mooc thành công (boolean c = true)
        if (c) {
            // Chuyển hướng (redirect) về trang quản lý Mooc của khóa học sau khi thêm thành công
            response.sendRedirect("mooc?courseID=" + courseid);
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
