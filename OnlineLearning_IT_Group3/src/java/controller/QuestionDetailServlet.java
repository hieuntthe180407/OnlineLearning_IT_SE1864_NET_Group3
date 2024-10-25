/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AnswerDAO;
import dal.CourseDAO;
import dal.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import model.Answer;
import model.Course;
import model.Question;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuestionDetailServlet", urlPatterns = {"/QuestionDetailServlet"})
@MultipartConfig
public class QuestionDetailServlet extends HttpServlet {

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
        String questionIdParam = request.getParameter("questionId");
        if (questionIdParam != null) {
            int questionId = Integer.parseInt(questionIdParam);

            QuestionDAO questionDAO = new QuestionDAO();
            AnswerDAO answerDAO = new AnswerDAO();

            Answer answer = new Answer();
            Question question = new Question();

            answer = answerDAO.getAnswerInfo(questionId);
            question = questionDAO.getQuestionInfo(questionId);

            request.setAttribute("questionDetailInfo", question);
            request.setAttribute("answerDetailInfo", answer);
            request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
        }

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
        String questionIdParam = request.getParameter("questionId");
        int questionId = Integer.parseInt(questionIdParam);

        String answerIdParam = request.getParameter("answerId");
        int answerId = Integer.parseInt(answerIdParam);

        String questionTitle = request.getParameter("questionTitle");

        String questionCourse = request.getParameter("questionCourse");
        CourseDAO cDAO = new CourseDAO();
        if (!cDAO.checkCourseByName(questionCourse)) {
            request.setAttribute("errorDetail", "Course is not exsits.");
            request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
            return;
        }
        Course course = new Course();
        course.setCourseID(cDAO.courseIdByCourseName(questionCourse));

        String questionType = request.getParameter("questionType");

        String status = request.getParameter("status");

        String level = request.getParameter("level");

        String questionContent = request.getParameter("questionContent");

        String essayAnswer = request.getParameter("essayAnswer");

        String explanation = request.getParameter("explanation");

        String oldMedia = request.getParameter("oldMedia");
        Part mediaPart = request.getPart("media");

        String media = null;
        if (mediaPart == null || mediaPart.getSize() == 0) {
            media = oldMedia;
        } else {
            String realPath = request.getServletContext().getRealPath("/imgQuestion");

            if (!Files.exists(Path.of(realPath))) {
                Files.createDirectory(Path.of(realPath));
            }

            String fileName = Path.of(mediaPart.getSubmittedFileName()).getFileName().toString();
            Path targetPath = Path.of(realPath, fileName); // kết hợp đường dẫn project với tên tệp

            if (Files.exists(targetPath)) {
                String newFileName = System.currentTimeMillis() + "_" + fileName; // Thêm timestamp
                targetPath = Path.of(realPath, newFileName); // Cập nhật đường dẫn đích
            }
            mediaPart.write(targetPath.toString());
            media = "imgQuestion/" + targetPath.getFileName().toString();
        }
        Question q = new Question();
        q.setQuestionId(questionId);
        q.setQuestionContent(questionContent);
        q.setQuestionTitle(questionTitle);
        q.setCourse(course);
        q.setQuestionType(questionType);
        q.setQuestionImgOrVideo(media);
        q.setLevel(level);
        q.setStatus(status);
        q.setExplanation(explanation);
        QuestionDAO qDao = new QuestionDAO();
        qDao.updateQuestion(q);

        if (questionType.equals("Essay")) {
            Answer a = new Answer();
            a.setAnswerId(answerId);
            a.setQuestion(q);
            a.setOptionContent(essayAnswer);
            AnswerDAO aDao = new AnswerDAO();
            aDao.updateEssayAnswer(a);
        }

        response.sendRedirect("QuestionListServlet");

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
