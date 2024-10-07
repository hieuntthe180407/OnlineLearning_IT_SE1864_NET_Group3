/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author ADMIN
 */
import java.util.*;
import java.lang.*;
public class Question {
    
    private int questionId;
    private String questionContent;
    private String questionType;
    private String questionImgOrVideo;
    private String level;
    private String Statusl;
    private String correctAnswer;
    private Course course;

    public Question() {
    }

    public Question(int questionId, String questionContent, String questionType, String questionImgOrVideo, String level, String Statusl, String correctAnswer, Course course) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.questionImgOrVideo = questionImgOrVideo;
        this.level = level;
        this.Statusl = Statusl;
        this.correctAnswer = correctAnswer;
        this.course = course;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionImgOrVideo() {
        return questionImgOrVideo;
    }

    public void setQuestionImgOrVideo(String questionImgOrVideo) {
        this.questionImgOrVideo = questionImgOrVideo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatusl() {
        return Statusl;
    }

    public void setStatusl(String Statusl) {
        this.Statusl = Statusl;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", questionContent=" + questionContent + ", questionType=" + questionType + ", questionImgOrVideo=" + questionImgOrVideo + ", level=" + level + ", Statusl=" + Statusl + ", correctAnswer=" + correctAnswer + ", course=" + course.getCourseName() + '}';
    }
    
    

}
