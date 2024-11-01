/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.CategoryBlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Blog;
import model.CategoryBlog;
import util.Upload;

/**
 *
 * @author HP
 */
@WebServlet(name = "BlogManageController", urlPatterns = {"/BlogManageController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)
public class BlogManageController extends HttpServlet {
    // Khai báo BlogDAO để truy xuất dữ liệu liên quan đến blog
    private BlogDAO blogDAO = new BlogDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy tham số hành động (action) từ yêu cầu (request)
        String action = req.getParameter("action");

        // Xử lý khi hành động là "viewDetail" để xem chi tiết blog
        if ("viewDetail".equals(action)) {
            int blogId = Integer.parseInt(req.getParameter("id"));
            Blog blog = blogDAO.viewBlogDetail(blogId);
            req.setAttribute("blog", blog);
            req.getRequestDispatcher("/blogView.jsp").forward(req, resp);
            
            // Xử lý khi hành động là "edit" để chỉnh sửa blog
        } else if ("edit".equals(action)) {
            CategoryBlogDAO categoryDao = new CategoryBlogDAO();
            List<CategoryBlog> categoryBlog = categoryDao.getAllCategories();
            req.setAttribute("categoryBlogs", categoryBlog);
            int blogId = Integer.parseInt(req.getParameter("id"));
            Blog blog = blogDAO.viewBlogDetail(blogId);
            req.setAttribute("blog", blog);
            req.getRequestDispatcher("/blogForm.jsp").forward(req, resp);
        }
        
        // Xử lý khi hành động là "add" để thêm blog mới
        if ("add".equals(action)) {
            CategoryBlogDAO categoryDao = new CategoryBlogDAO();
            List<CategoryBlog> categoryBlog = categoryDao.getAllCategories();
            req.setAttribute("categoryBlogs", categoryBlog);
            req.getRequestDispatcher("/blogAddForm.jsp").forward(req, resp);
            
            // Mặc định là hiển thị danh sách blog
        } else {
            List<Blog> blogs = blogDAO.getAllPost();
            req.setAttribute("blogs", blogs);
            req.getRequestDispatcher("/blogManageList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Lấy tham số hành động (action) từ yêu cầu (request)
        String action = req.getParameter("action");
        
        // Xử lý khi hành động là "add" để thêm blog mới
        if ("add".equals(action)) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String status = req.getParameter("status");
            Part imageUrl = req.getPart("featuredImage");
            
            // Đường dẫn lưu ảnh blog
            String pathBlog = "./uploads/blog/";
            Upload upload = new Upload();
            String uploadPath = getServletContext().getRealPath(pathBlog);
            String nameImgBanner = upload.uploadImg(imageUrl, uploadPath);

            if (nameImgBanner != null) {
                nameImgBanner = pathBlog + nameImgBanner;
            }

            // Tạo đối tượng Blog và thiết lập thông tin
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setStatus(status);
            blog.setFeaturedImage(nameImgBanner);

            boolean isAdded = blogDAO.addBlog(blog);

            // Thiết lập thông báo về trạng thái thêm blog
            if (isAdded) {
                req.setAttribute("message", "Blog added successfully!");
            } else {
                req.setAttribute("message", "Failed to add blog.");
            }

            resp.sendRedirect("BlogManageController");
            // Xử lý khi hành động là "update" để cập nhật blog
        } else if ("update".equals(action)) {
            int blogId = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String status = req.getParameter("status");
            String oldImage = req.getParameter("oldImage");
            Part imageUrl = req.getPart("featuredImage");
            
            // Đường dẫn lưu ảnh blog                                       
            String pathBlog = "./uploads/blog/";
            Upload upload = new Upload();
            String uploadPath = getServletContext().getRealPath(pathBlog);
            String nameImgBanner = upload.uploadImg(imageUrl, uploadPath);
            
            // Nếu không có ảnh mới, sử dụng ảnh cũ
            if (nameImgBanner == null) {
                nameImgBanner = oldImage;
            } else {
                nameImgBanner = pathBlog + nameImgBanner;
            }
            
            // Tạo đối tượng Blog và thiết lập thông tin
            Blog blog = new Blog();
            blog.setBlogId(blogId);
            blog.setTitle(title);
            blog.setContent(content);
            blog.setStatus(status);
            blog.setFeaturedImage(nameImgBanner);

            boolean isUpdated = blogDAO.updateBlog(blog);

            // Thiết lập thông báo về trạng thái cập nhật blog
            if (isUpdated) {
                req.setAttribute("message", "Blog updated successfully!");
            } else {
                req.setAttribute("message", "Failed to update blog.");
            }

            Blog updatedBlog = blogDAO.viewBlogDetail(blogId);
            req.setAttribute("blog", updatedBlog);
            resp.sendRedirect("BlogManageController");
        }
    }
}
