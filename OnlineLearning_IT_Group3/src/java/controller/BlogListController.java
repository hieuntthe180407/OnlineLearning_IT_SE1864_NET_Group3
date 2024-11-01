/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Blog;
import model.CategoryBlog;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="BlogListController", urlPatterns={"/blogs"})
public class BlogListController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet BlogListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogListController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
    // Định nghĩa kích thước trang cho phân trang
    private static final int PAGE_SIZE=5;
    private BlogDAO blogDAO;
    
    @Override
    public void init() throws ServletException {
        // Khởi tạo BlogDAO để truy xuất dữ liệu
        blogDAO = new BlogDAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        // Lấy từ khóa tìm kiếm nếu có từ yêu cầu
        String searchKeyword = req.getParameter("search");
        int page = 1;
        // Chuyển đổi số trang từ yêu cầu, mặc định là 1 nếu có lỗi
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
        }
          // Danh sách blog và tổng số lượng blog
        List<Blog> blogs;
        int totalBlogs;
        
        // Nếu có từ khóa tìm kiếm, thực hiện tìm kiếm, ngược lại lấy danh sách blog mặc định
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            blogs = blogDAO.searchBlogs(searchKeyword, page, PAGE_SIZE);
            totalBlogs = blogDAO.getSearchBlogCount(searchKeyword);
        } else {
            blogs = blogDAO.getBlogs(page, PAGE_SIZE);
            totalBlogs = blogDAO.getTotalBlogCount();
        }
// Tính toán tổng số trang
        int totalPages = (int) Math.ceil((double) totalBlogs / PAGE_SIZE);
        
        // Lấy danh sách các danh mục và blog mới nhất
        List<CategoryBlog> categories = blogDAO.getCategories();
        List<Blog> latestBlogs = blogDAO.getLatestBlogs(5);

        // Đặt các thuộc tính vào yêu cầu để hiển thị trên trang JSP
        req.setAttribute("blogs", blogs);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("categories", categories);
        req.setAttribute("latestBlogs", latestBlogs);
        req.setAttribute("searchKeyword", searchKeyword); 

        // Chuyển hướng tới trang JSP để hiển thị dữ liệu blog
        req.getRequestDispatcher("blogList.jsp").forward(req, resp);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
