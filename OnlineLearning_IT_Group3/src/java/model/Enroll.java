/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author laptop acer
 */
public class Enroll {
    private int UserID,CourseID, Progress;
    private String Status, DateEnroll;
    User user;
    Course course;
    public Enroll() {
    }
    public Enroll(int UserID, int CourseID, int Progress, String Status, String DateEnroll, User user, Course course) {
        this.UserID = UserID;
        this.CourseID = CourseID;
        this.Progress = Progress;
        this.Status = Status;
        this.DateEnroll = DateEnroll;
        this.user = user;
        this.course = course;
    }
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    public int getCourseID() {
        return CourseID;
    }
    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }
    public int getProgress() {
        return Progress;
    }
    public void setProgress(int Progress) {
        this.Progress = Progress;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public String getDateEnroll() {
        return DateEnroll;
    }
    public void setDateEnroll(String DateEnroll) {
        this.DateEnroll = DateEnroll;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    
}
