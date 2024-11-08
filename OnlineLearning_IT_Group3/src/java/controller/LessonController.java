package controller;

import dal.CourseDAO;
import dal.LessonDAO;
import model.Course;
import model.Lesson;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "LessonController", urlPatterns = {"/lessonList"})
public class LessonController extends HttpServlet {
    
    // Khởi tạo các đối tượng DAO để truy xuất dữ liệu từ cơ sở dữ liệu
    private LessonDAO lessonDAO = new LessonDAO();
    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Lấy tham số "courseID" từ yêu cầu và chuyển sang kiểu int
        String courseIdStr = request.getParameter("courseID");
        int courseId = Integer.parseInt(courseIdStr);
        
        // Lấy thông tin khóa học từ courseDAO dựa trên courseId và đặt vào yêu cầu
        Course course = courseDAO.getCourseByID(courseId);
        request.setAttribute("Course", course);
        
        // Lấy danh sách các bài học của khóa học từ lessonDAO dựa trên courseId
        List<Lesson> lessons = lessonDAO.getAlllessonBycourseID(courseId);
        
        // Sắp xếp danh sách bài học vào các module theo số thứ tự của bài học
        Map<String, List<Lesson>> modules = new LinkedHashMap<>();
        for (Lesson lesson : lessons) {
            String moduleName = "Module " + lesson.getLessonNumber(); // Tạo tên module dựa trên số thứ tự bài học
            modules.putIfAbsent(moduleName, new ArrayList<>()); // Thêm module vào map nếu chưa có
            modules.get(moduleName).add(lesson); // Thêm bài học vào module tương ứng
        }
       
        // Đặt danh sách module vào yêu cầu để hiển thị trong JSP
        request.setAttribute("modules", modules);
        
        // Chuyển hướng đến trang JSP để hiển thị danh sách bài học
        RequestDispatcher dispatcher = request.getRequestDispatcher("lessonView.jsp");
        dispatcher.forward(request, response);
    }
}
