package controller;
import model.Lesson;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.google.gson.Gson;
import dal.LessonDAO;

@WebServlet(name = "LessonDetailController", urlPatterns = {"/LessonDetailController"})
public class LessonDetailController extends HttpServlet {
    
    // Khởi tạo đối tượng LessonDAO để truy xuất dữ liệu bài học từ cơ sở dữ liệu                               
    private LessonDAO lessonDAO = new LessonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Lấy tham số "lessonID" từ yêu cầu (request) và chuyển đổi thành kiểu int
        String lessonIdStr = request.getParameter("lessonID");
        int lessonID = Integer.parseInt(lessonIdStr);
        
        // Lấy thông tin chi tiết của bài học từ lessonDAO dựa trên lessonID
        Lesson lesson = lessonDAO.getLessonByID(lessonID);
        
        // Chuyển đổi đối tượng bài học thành JSON để gửi về client
        Gson gson = new Gson();
        String lessonJson = gson.toJson(lesson);
        
        // Thiết lập loại nội dung trả về là JSON và gửi dữ liệu JSON về client
        response.setContentType("application/json");
        response.getWriter().write(lessonJson);
    }
}
