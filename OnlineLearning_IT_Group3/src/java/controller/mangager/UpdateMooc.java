/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mangager;

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
@WebServlet(name = "UpdateMooc", urlPatterns = {"/updateMooc"})
public class UpdateMooc extends HttpServlet {

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
            out.println("<title>Servlet UpdateMooc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMooc at " + request.getContextPath() + "</h1>");
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
        // Lấy các tham số từ URL, bao gồm moocid, courseid, moocnumber và name
        int moocid = Integer.parseInt(request.getParameter("moocid"));
        int courseid = Integer.parseInt(request.getParameter("courseid"));
        int moocnumber = Integer.parseInt(request.getParameter("moocnumber"));
        String name = request.getParameter("name");

        // Lưu các tham số vào request để có thể sử dụng trong trang JSP
        request.setAttribute("moocid", moocid); // Lưu moocid vào request
        request.setAttribute("courseid", courseid); // Lưu courseid vào request
        request.setAttribute("lastnumber", moocnumber); // Lưu moocnumber vào request
        request.setAttribute("name", name); // Lưu name vào request

        // Chuyển hướng yêu cầu đến trang updateMooc.jsp để hiển thị thông tin
        request.getRequestDispatcher("updateMooc.jsp").forward(request, response);
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
        // Lấy các tham số được gửi từ form hoặc URL
        String courseId = request.getParameter("courseid"); // Lấy courseid từ tham số yêu cầu
        String moocNumber = request.getParameter("moocnumber"); // Lấy moocnumber từ tham số yêu cầu
        String moocName = request.getParameter("moocname"); // Lấy moocname từ tham số yêu cầu
        String moocid = request.getParameter("moocid"); // Lấy moocid từ tham số yêu cầu

        // Tạo đối tượng MoocDAO để thực hiện thao tác với cơ sở dữ liệu
        MoocDAO mdao = new MoocDAO();

        // Cập nhật thông tin Mooc trong cơ sở dữ liệu, sử dụng moocid và moocName
        boolean update = mdao.updateMooc(moocid, moocName); // Gọi phương thức updateMooc để cập nhật

        // Chuyển hướng người dùng về trang mooc với courseID tương ứng
        response.sendRedirect("mooc?courseID=" + courseId); // Sau khi cập nhật, chuyển hướng về danh sách Mooc của khóa học
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
