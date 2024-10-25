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
import java.sql.*;
import model.Answer;
import model.Question;

public class AnswerDAO extends DBContext {

    public void importAnswer(Answer answer, boolean isCorrect) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO [Answer] ("
                    + "OptionContent"
                    + ",IsCorrect"
                    + ",QuestionID)\n"
                    + "VALUES (?,?,?)";
            st = connection.prepareStatement(sql);
            st.setString(1, answer.getOptionContent());
            st.setBoolean(2, isCorrect);
            st.setInt(3, answer.getQuestion().getQuestionId());
            st.executeUpdate();
        } catch (Exception e) {

        }

    }

    public Answer getAnswerInfo(int questionId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Answer a = new Answer();
        try {
            String sql = "Select a.AnswerID, a.OptionContent, a.IsCorrect "
                    + "from Answer a "
                    + "where a.QuestionID =?";
            st = connection.prepareStatement(sql);
            st.setInt(1, questionId);
            rs = st.executeQuery();
            while (rs.next()) {
                a.setAnswerId(rs.getInt("AnswerID"));
                a.setOptionContent(rs.getString("OptionContent"));
                a.setIsCorrect(rs.getBoolean("IsCorrect"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;

    }

    public void removeAnswerByQuestionId(int questionId) {
        PreparedStatement st = null;
        try {
            String sql = "DELETE FROM Answer WHERE QuestionID = ?";
            st = connection.prepareStatement(sql);
            st.setInt(1, questionId);
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void updateEssayAnswer(Answer a) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE Answer SET OptionContent = ? WHERE AnswerID = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, a.getOptionContent());
            st.setInt(2, a.getAnswerId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        Question question = new Question();
        question.setQuestionId(3);

        Answer answer = new Answer();
        answer.setOptionContent("2");
        answer.setQuestion(question);
        AnswerDAO a = new AnswerDAO();
        a.importAnswer(answer, false);
    }

}
