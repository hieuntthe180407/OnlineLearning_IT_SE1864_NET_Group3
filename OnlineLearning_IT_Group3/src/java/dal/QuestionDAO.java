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
import java.sql.*;
import model.Question;

public class QuestionDAO extends DBContext {

    public int importQuestion(Question question) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int lastIndex = 0;
        try {
            String sql = "INSERT INTO [Question] ("
                    + "QuestionContent"
                    + ", QuestionType"
                    + ", QuestionImgOrVideo"
                    + ", [Level]"
                    + ", [Status]"
                    + ", QuestionTitle"
                    + ", CourseID"
                    + ", Explanation) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, question.getQuestionContent());
            st.setString(2, question.getQuestionType());
            st.setString(3, question.getQuestionImgOrVideo());
            st.setString(4, question.getLevel());
            st.setString(5, question.getStatus());
            st.setString(6, question.getQuestionTitle());
            st.setInt(7, question.getCourse().getCourseID());
            st.setString(8, question.getExplanation());

            // Execute the INSERT statement
            int affectedRows = st.executeUpdate();

            // Check if the insertion was successful and get the generated key
            if (affectedRows > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    lastIndex = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error
        }
        return lastIndex;
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
        String sql = "SELECT q.QuestionID"
                + ", q.QuestionContent"
                + ", q.QuestionType"
                + ", q.QuestionImgOrVideo"
                + ", q.Level"
                + ", q.Status"
                + ", c.CourseName FROM Question q, Course c WHERE q.CourseID = c.CourseID";
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

        // Tạo một Course giả định để sử dụng
        Course course = new Course();
        course.setCourseID(1); // Đặt ID cho Course mà bạn muốn thêm câu hỏi

        // Tạo một Question mới
        Question newQuestion = new Question();
        newQuestion.setQuestionContent("What is the capital of Vietnam?");
        newQuestion.setQuestionType("Multiple Choice");
        newQuestion.setQuestionImgOrVideo(""); // Nếu không có ảnh/video thì để trống
        newQuestion.setLevel("Easy");
        newQuestion.setStatus("Visible");
        newQuestion.setQuestionTitle("Capital Question");
        newQuestion.setCourse(course);
        newQuestion.setExplanation("Hanoi is the capital city of Vietnam.");

        // Gọi phương thức importQuestion để thêm câu hỏi vào cơ sở dữ liệu
        int lastIndex = questionDAO.importQuestion(newQuestion);
        System.out.println("New question added with ID: " + lastIndex);

    }

}
