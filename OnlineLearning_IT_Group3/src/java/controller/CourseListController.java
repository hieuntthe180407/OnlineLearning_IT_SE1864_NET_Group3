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
import java.util.List;
import model.Category;
import model.Course;

/**
 *
 * @author DTC
 */
@WebServlet(name="CourseListController", urlPatterns={"/courseList"})
public class CourseListController extends HttpServlet {
   
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
            out.println("<title>Servlet CourseListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseListController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Tạo đối tượng CourseDAO để thao tác với dữ liệu khóa học trong cơ sở dữ liệu
    CourseDAO cDao = new CourseDAO();

    // Lấy danh sách 8 danh mục khóa học phổ biến nhất
    List<Category> listTop8Category = cDao.getTop8Category();
    // Đặt danh sách 8 danh mục vào thuộc tính request để truyền sang trang JSP
    request.setAttribute("listTop8Category", listTop8Category);

    // Khai báo danh sách khóa học để lưu kết quả tìm kiếm hoặc lọc
    List<Course> listAllCourse = null;

    // Lấy tham số "action" từ yêu cầu của người dùng, xác định loại thao tác mà người dùng muốn thực hiện
    String action = request.getParameter("action");

    // Kiểm tra nếu "action" là null hoặc rỗng (không có hành động cụ thể), lấy toàn bộ danh sách khóa học
    if (action == null || action.isEmpty()) {
        listAllCourse = cDao.getAllCourse();
        
    } else if (action.equalsIgnoreCase("search")) {
        // Nếu hành động là "search" (tìm kiếm), lấy từ khóa tìm kiếm từ tham số "text" và tìm các khóa học chứa từ khóa này
        String text = request.getParameter("text");
        listAllCourse = cDao.getAllCourseBySearch(text);
        
    } else if (action.equalsIgnoreCase("category")) {
        // Nếu hành động là "category" (tìm kiếm theo danh mục), lấy tên danh mục từ tham số "name" và tìm các khóa học trong danh mục đó
        String name = request.getParameter("name");
        listAllCourse = cDao.getAllCoursesByCategory(name);
        
    } else if (action.equalsIgnoreCase("filterPrice")) {
        // Nếu hành động là "filterPrice" (lọc theo giá), lấy khoảng giá tối thiểu và tối đa từ các tham số "minPrice" và "maxPrice"
        double minPrice = Double.parseDouble(request.getParameter("minPrice"));
        double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
        
        // Gọi phương thức getCourseByMinMaxPrice để lấy các khóa học có giá nằm trong khoảng minPrice và maxPrice
        listAllCourse = cDao.getCourseByMinMaxPrice(minPrice, maxPrice);
    }

    // Lấy giá cao nhất của các khóa học từ cơ sở dữ liệu để hiển thị trên giao diện lọc
    double maxPrice = cDao.getMaxPrice();
    request.setAttribute("maxPrice", maxPrice);

    // Lấy giá thấp nhất của các khóa học từ cơ sở dữ liệu để hiển thị trên giao diện lọc
    double minPrice = cDao.getMinPrice();
    request.setAttribute("minPrice", minPrice);

    // Đặt danh sách các khóa học (sau khi tìm kiếm hoặc lọc) vào thuộc tính request để truyền sang trang JSP
    request.setAttribute("listCourse", listAllCourse);

    // Chuyển tiếp yêu cầu và dữ liệu đến trang "courseList.jsp" để hiển thị kết quả
    request.getRequestDispatcher("/courseList.jsp").forward(request, response);
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
