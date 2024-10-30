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
    private LessonDAO lessonDAO = new LessonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lessonIdStr = request.getParameter("lessonID");
        int lessonID = Integer.parseInt(lessonIdStr);
        Lesson lesson = lessonDAO.getLessonById(lessonID);
        Gson gson = new Gson();
        String lessonJson = gson.toJson(lesson);
        response.setContentType("application/json");
        response.getWriter().write(lessonJson);
    }
}
