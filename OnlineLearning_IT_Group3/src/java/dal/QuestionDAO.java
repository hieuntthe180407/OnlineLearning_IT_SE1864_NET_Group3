/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author ADMIN
 */
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Course;
import model.Question;

public class QuestionDAO extends DBContext {

    public void importQuestion(Question question) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO [Question] ("
                    + "QuestionContent"
                    + ",QuestionType"
                    + ",QuestionImgOrVideo"
                    + ",[Level]"
                    + ",[Status]"
                    + ",CourseID)\n"
                    + "VALUES (?,?,?,?,?,?)";

            st = connection.prepareStatement(sql);

            st.setString(1, question.getQuestionContent());
            st.setString(2, question.getQuestionType());
            st.setString(3, question.getQuestionImgOrVideo());
            st.setString(4, question.getLevel());
            st.setString(5, question.getStatus());
            st.setInt(6, question.getCourse().getCourseID());
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void updateStatusQuestion(int questionId, String status) {
        PreparedStatement st = null;
        String sql = "UPDATE Question SET Status = ? WHERE QuestionId = ?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, questionId);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public List<Question> getFilteredQuestions(String content, String course, String level, String status, int page, int numberQuestion) {
        List<Question> listQuestion = new ArrayList<>();
        PreparedStatement st = null;
        String sql = "SELECT q.QuestionID, q.QuestionContent, q.QuestionType, q.QuestionImgOrVideo, q.Level, q.Status, q.CorrectAnswer, c.CourseName FROM Question q, Course c WHERE q.CourseID = c.CourseID";
        //Tạo list lưu câu lệnh
        List<String> params = new ArrayList<>();

        if (content != null && !content.isEmpty()) {
            sql += " AND q.QuestionContent LIKE ?";
            params.add("%" + content + "%");
        }
        if (course != null && !course.isEmpty()) {
            sql += " AND c.CourseName LIKE ?";
            params.add("%" + course + "%");
        }
        if (level != null && !level.isEmpty()) {
            sql += " AND q.Level = ?";
            params.add(level);
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND q.Status = ?";
            params.add(status);
        }

        //Câu hỏi/ trang
        sql += " ORDER BY q.QuestionID ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            st = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                st.setString(i + 1, params.get(i)); //Truyền tham số vào lệnh
            }
            int offset = (page - 1) * numberQuestion;
            st.setInt(params.size() + 1, offset);         // Những câu hỏi trang 1,2,3
            st.setInt(params.size() + 2, numberQuestion); // Số câu hỏi/ trang

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Question qs = new Question();
                qs.setQuestionId(rs.getInt("QuestionID"));
                qs.setQuestionContent(rs.getString("QuestionContent"));
                qs.setQuestionType(rs.getString("QuestionType"));
                qs.setQuestionImgOrVideo(rs.getString("QuestionImgOrVideo"));
                qs.setLevel(rs.getString("Level"));
                qs.setStatus(rs.getString("Status"));
                Course cr = new Course();
                cr.setCourseName(rs.getString("CourseName"));
                qs.setCourse(cr);
                listQuestion.add(qs);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return listQuestion;
    }

    public int getTotalPages(int numPerPage) {
        PreparedStatement st = null;
        int totalquestion = 0;
        String sql = "SELECT COUNT(*) AS total FROM Question";
        int totalPage = 0;
        try {
            st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalquestion = rs.getInt("total");
            }

            // Tính số trang
            totalPage = (int) Math.ceil((double) totalquestion / numPerPage); //Math.ceil để làm tròn lên
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPage;

    }

    public static void main(String[] args) {

        QuestionDAO questionDAO = new QuestionDAO();

        String content = "";
        String course = "";
        String level = "";
        String status = "";

        List<Question> questions = questionDAO.getFilteredQuestions(content, course, level, status, 2, 3);

        // Print out the questions
        for (Question q : questions) {
            System.out.println("Question ID: " + q.getQuestionId());
            System.out.println("Content: " + q.getQuestionContent());
            System.out.println("Course: " + q.getCourse().getCourseName());
            System.out.println("Level: " + q.getLevel());
            System.out.println("Status: " + q.getStatus());
            System.out.println("--------------------------------------------------");
        }

    }

}
