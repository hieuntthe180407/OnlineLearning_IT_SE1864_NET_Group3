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
//    public Answer getAnswerInfo(int questionId){
//        
//    }

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
