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

    //Chỉnh sửa trạng thái của question 
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

//Duyệt câu hỏi theo filter
    public List<Question> getFilteredQuestions(String title, String course, String questionType, String level, String status, int page, int numberQuestion, int roleId, int userId) {
        List<Question> listQuestion = new ArrayList<>();
        PreparedStatement st = null;
        String sql = "SELECT q.QuestionID"
                + ", q.QuestionTitle"
                + ", q.QuestionType"
                + ", q.QuestionImgOrVideo"
                + ", q.Level"
                + ", q.Status"
                + ", c.CourseName FROM Question q, Course c WHERE q.CourseID = c.CourseID";
        //Tạo list lưu câu lệnh
        List<String> params = new ArrayList<>();

        if (roleId == 2) { // Nếu là Teacher, lọc theo userId
            sql += " AND c.UserID = ?";
            params.add(String.valueOf(userId));
        }

        if (title != null && !title.isEmpty()) {
            sql += " AND q.QuestionTitle LIKE ?";
            params.add("%" + title + "%");
        }
        if (course != null && !course.isEmpty()) {
            sql += " AND c.CourseName LIKE ?";
            params.add("%" + course + "%");
        }
        if (questionType != null && !questionType.isEmpty()) {
            sql += " AND q.QuestionType = ?";
            params.add(questionType);
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
                qs.setQuestionTitle(rs.getString("QuestionTitle"));
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
//Lấy tổng số trang ( có thể phụ thuộc vào filter)

    public int getTotalPages(String title, String course, String questionType, String level, String status, int numberQuestion, int roleId, int userId) {
        PreparedStatement st = null;
        String sql = "SELECT COUNT(*) FROM Question q, Course c WHERE q.CourseID = c.CourseID";
        List<String> params = new ArrayList<>();
        
        if (roleId == 2) { // Nếu là Teacher, lọc theo userId
            sql += " AND c.UserID = ?";
            params.add(String.valueOf(userId));
        }

        // Điều kiện lọc tương tự như trong getFilteredQuestions
        if (title != null && !title.isEmpty()) {
            sql += " AND q.QuestionTitle LIKE ?";
            params.add("%" + title + "%");
        }
        if (course != null && !course.isEmpty()) {
            sql += " AND c.CourseName LIKE ?";
            params.add("%" + course + "%");
        }
        if (questionType != null && !questionType.isEmpty()) {
            sql += " AND q.QuestionType = ?";
            params.add(questionType);
        }
        if (level != null && !level.isEmpty()) {
            sql += " AND q.Level = ?";
            params.add(level);
        }

        if (status != null && !status.isEmpty()) {
            sql += " AND q.Status = ?";
            params.add(status);
        }

        try {
            st = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                st.setString(i + 1, params.get(i));
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int totalQuestions = rs.getInt(1);
                return (int) Math.ceil((double) totalQuestions / numberQuestion); // Tính tổng số trang
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public Question getQuestionInfo(int questionId) {
        Question question = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT q.QuestionID,q.QuestionContent, q.QuestionTitle, q.QuestionType, "
                + "q.QuestionImgOrVideo, q.Level, q.Status, q.Explanation, c.CourseName "
                + "FROM Question q,Course c  "
                + "WHERE q.QuestionID = ? AND c.CourseID = q.CourseID";
        try {
            st = connection.prepareStatement(sql);
            st.setInt(1, questionId); // Set the question ID
            rs = st.executeQuery();

            if (rs.next()) {
                question = new Question();
                question.setQuestionId(rs.getInt("QuestionID"));
                question.setQuestionContent(rs.getString("QuestionContent"));
                question.setQuestionTitle(rs.getString("QuestionTitle"));
                question.setQuestionType(rs.getString("QuestionType"));
                question.setQuestionImgOrVideo(rs.getString("QuestionImgOrVideo"));
                question.setLevel(rs.getString("Level"));
                question.setStatus(rs.getString("Status"));
                question.setExplanation(rs.getString("Explanation"));

                // Create and set Course object
                Course course = new Course();
                course.setCourseName(rs.getString("CourseName"));
                question.setCourse(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public void removeQuestionByQuestionId(int questionId) {
        PreparedStatement st = null;
        try {
            String sql = "DELETE FROM Question WHERE QuestionID = ?";
            st = connection.prepareStatement(sql);
            st.setInt(1, questionId);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateQuestion(Question question) {
        PreparedStatement st = null;
        String sql = "UPDATE Question SET "
                + "QuestionContent = ?, "
                + "QuestionType = ?, "
                + "QuestionImgOrVideo = ?, "
                + "[Level] = ?, "
                + "Status = ?, "
                + "QuestionTitle = ?, "
                + "Explanation = ?, "
                + "CourseID = ? "
                + "WHERE QuestionID = ?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, question.getQuestionContent());
            st.setString(2, question.getQuestionType());
            st.setString(3, question.getQuestionImgOrVideo());
            st.setString(4, question.getLevel());
            st.setString(5, question.getStatus());
            st.setString(6, question.getQuestionTitle());
            st.setString(7, question.getExplanation());
            st.setInt(8, question.getCourse().getCourseID());
            st.setInt(9, question.getQuestionId());

            st.executeUpdate(); // Execute the update

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {

        QuestionDAO questionDAO = new QuestionDAO();

        // Tạo một Course giả định để sử dụng
        int testQuestionId = 3; // Thay đổi ID này cho câu hỏi bạn muốn kiểm tra
        Question question = questionDAO.getQuestionInfo(testQuestionId);

        // In ra thông tin câu hỏi
        if (question != null) {
            System.out.println("Question ID: " + question.getQuestionId());
            System.out.println("Question Title: " + question.getQuestionTitle());
            System.out.println("Question Content: " + question.getQuestionContent());
            System.out.println("Question Type: " + question.getQuestionType());
            System.out.println("Level: " + question.getLevel());
            System.out.println("Status: " + question.getStatus());
            System.out.println("Explanation: " + question.getExplanation());
            System.out.println("Course Name: " + question.getCourse().getCourseName());
        } else {
            System.out.println("No question found with ID: " + testQuestionId);
        }

    }

}
