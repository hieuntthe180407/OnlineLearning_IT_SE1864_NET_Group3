/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.questionBank;

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
import java.util.ArrayList;
import java.util.List;
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

            Question question = new Question();
            question = questionDAO.getQuestionInfo(questionId);
            // Nếu câu hỏi là dạng "Essay"
            if (question.getQuestionType().equals("Essay")) {
                AnswerDAO answerDAO = new AnswerDAO();
                Answer answer = answerDAO.getAnswerInfo(questionId);
                request.setAttribute("answerDetail", answer);
            } else {
                // Nếu câu hỏi là dạng "Multiple Choice"
                AnswerDAO aDao = new AnswerDAO();
                List<Answer> listOption = aDao.listAnswerOption(questionId);
                request.setAttribute("answerDetailInfo", listOption);

            }
            request.setAttribute("questionDetailInfo", question);
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
        // Lấy ID của câu hỏi
        String questionIdParam = request.getParameter("questionId");
        int questionId = Integer.parseInt(questionIdParam);
        // Lấy tiêu đề câu hỏi
        String questionTitle = request.getParameter("questionTitle");
        // Kiểm tra và lấy thông tin khóa học liên quan đến câu hỏi
        String questionCourse = request.getParameter("questionCourse");
        CourseDAO cDAO = new CourseDAO();
        if (!cDAO.checkCourseByName(questionCourse)) {
            request.setAttribute("errorDetail", "Course is not exists.");
            request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
            return;
        }
        Course course = new Course();
        course.setCourseID(cDAO.courseIdByCourseName(questionCourse));
        // Lấy các thông tin khác của câu hỏi

        String questionType = request.getParameter("questionType");

        String status = request.getParameter("status");

        String level = request.getParameter("level");

        String questionContent = request.getParameter("questionContent");
        if (questionContent == null || questionContent.isEmpty()) {
            request.setAttribute("errorDetail", "Question content can not blank.");
            request.getRequestDispatcher("questionDetail.jsp").forward(request, response);
            return;
        }

        String explanation = request.getParameter("explanation");

        // Xử lý phần media (hình ảnh hoặc video) của câu hỏi
        String oldMedia = request.getParameter("oldMedia");
        Part mediaPart = request.getPart("media");
        String media = null;
        if (mediaPart == null || mediaPart.getSize() == 0) {

            // Nếu không có tệp mới được upload, giữ lại media cũ.
            media = oldMedia;
        } else {

            // Lấy đường dẫn thực trong hệ thống tệp để lưu hình ảnh/video của câu hỏi
            String realPath = request.getServletContext().getRealPath("/imgQuestion");

            // Kiểm tra thư mục lưu trữ hình ảnh/video. Nếu chưa tồn tại, tạo thư mục mới.
            if (!Files.exists(Path.of(realPath))) {
                Files.createDirectory(Path.of(realPath));
            }
            // Lấy tên tệp từ tệp được upload, bỏ phần đường dẫn
            String fileName = Path.of(mediaPart.getSubmittedFileName()).getFileName().toString();
            Path targetPath = Path.of(realPath, fileName); // kết hợp đường dẫn project với tên tệp

            // Kiểm tra nếu tệp đã tồn tại trong thư mục
            if (Files.exists(targetPath)) {
                String newFileName = System.currentTimeMillis() + "_" + fileName; // Thêm timestamp
                targetPath = Path.of(realPath, newFileName); // Cập nhật đường dẫn đích
            }
            // Ghi tệp mới vào đường dẫn
            mediaPart.write(targetPath.toString());
            // Cập nhật đường dẫn lưu trữ hình ảnh/video để lưu trong cơ sở dữ liệu
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

        // Cập nhật câu hỏi trong cơ sở dữ liệu
        QuestionDAO qDao = new QuestionDAO();
        qDao.updateQuestion(q);
        AnswerDAO aDao = new AnswerDAO();

        // Nếu câu hỏi là loại "Essay", xử lý cập nhật đáp án dạng văn bản
        if (questionType.equals("Essay")) {
            String answerIdParam = request.getParameter("answerId");
            int answerId = Integer.parseInt(answerIdParam);
            String essayAnswer = request.getParameter("essayAnswer");
            Answer a = new Answer();
            a.setAnswerId(answerId);
            a.setQuestion(q);
            a.setOptionContent(essayAnswer);
            aDao.updateEssayAnswer(a);
        } else { // Nếu câu hỏi có nhiều lựa chọn
            String[] optionId = request.getParameterValues("answerOptionId");
            String[] optionContents = request.getParameterValues("answerOption");
            String correctAnswerId = request.getParameter("correctAnswer"); // Lấy ID của đáp án đúng

            List<Answer> answers = new ArrayList<>();

            for (int i = 0; i < optionContents.length; i++) {
                Answer answerOption = new Answer();

                if (optionId != null && optionId.length > i) {
                    int answerId = Integer.parseInt(optionId[i]); // Chuyển đổi mỗi optionId thành int
                    answerOption.setAnswerId(answerId);
                }

                answerOption.setQuestion(q);
                answerOption.setOptionContent(optionContents[i]);
                //Chuyển đổi AnswerId thành mảng so sánh xem AnswerId có bằng correctAnswerId không nếu có trả ra true và ngược lại
                answerOption.setIsCorrect(String.valueOf(answerOption.getAnswerId()).equals(correctAnswerId));

                answers.add(answerOption);
            }

            aDao.updateAnswerOptions(answers);
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
