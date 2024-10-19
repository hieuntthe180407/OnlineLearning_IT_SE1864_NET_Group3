/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.time.LocalDateTime;
/**
 *
 * @author DTC
 */
public class Review {
    private int reviewID;
    private User userID;
    
    private Course courseID;
    
    private int rating;
    
    private LocalDateTime time;
    private String reviewContent;
    private int isReport;

    public Review(int reviewID, User userID, Course courseID, int rating, LocalDateTime time, String reviewContent, int isReport) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.courseID = courseID;
        this.rating = rating;
        this.time = time;
        this.reviewContent = reviewContent;
        this.isReport = isReport;
    }

    public Review() {
    }


    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getIsReport() {
        return isReport;
    }

    public void setIsReport(int isReport) {
        this.isReport = isReport;
    }

}
