/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trong
 */
public class Review {
    private int ReviewID;
    private String ReviewContent;
    private User user;
    private Course course;

    public Review() {
    }

    public Review(int ReviewID, String ReviewContent) {
        this.ReviewID = ReviewID;
        this.ReviewContent = ReviewContent;
    }

    public int getReviewID() {
        return ReviewID;
    }

    public void setReviewID(int ReviewID) {
        this.ReviewID = ReviewID;
    }

    public String getReviewContent() {
        return ReviewContent;
    }

    public void setReviewContent(String ReviewContent) {
        this.ReviewContent = ReviewContent;
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
