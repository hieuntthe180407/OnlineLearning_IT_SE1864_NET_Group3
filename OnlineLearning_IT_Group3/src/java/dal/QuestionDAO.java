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
                    + ",CorrectAnswer"
                    + ",CourseID)\n"
                    + "VALUES (?,?,?,?,?,?,?)";

            st = connection.prepareStatement(sql);

            st.setString(1, question.getQuestionContent());
            st.setString(2, question.getQuestionType());
            st.setString(3, question.getQuestionImgOrVideo());
            st.setString(4, question.getLevel());
            st.setString(5, question.getStatus());
            st.setString(6, question.getCorrectAnswer());
            st.setInt(7, question.getCourse().getCourseID());
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

}
