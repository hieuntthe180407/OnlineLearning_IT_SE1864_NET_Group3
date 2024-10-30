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
    private LessonDAO lessonDAO = new LessonDAO();
    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseID");
        int courseId = Integer.parseInt(courseIdStr);
        Course course = courseDAO.getCourseByID(courseId);
        request.setAttribute("Course", course);
        
        List<Lesson> lessons = lessonDAO.getLessonsByCourseId(courseId);
        
        Map<String, List<Lesson>> modules = new LinkedHashMap<>();
        for (Lesson lesson : lessons) {
            String moduleName = "Module " + lesson.getLessonNumber(); 
            modules.putIfAbsent(moduleName, new ArrayList<>());
            modules.get(moduleName).add(lesson);
        }
       
        request.setAttribute("modules", modules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lessonView.jsp");
        dispatcher.forward(request, response);
    }
}
