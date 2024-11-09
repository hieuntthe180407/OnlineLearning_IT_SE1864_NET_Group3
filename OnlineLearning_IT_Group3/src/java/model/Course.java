/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trong
 */
public class Course {
   private int courseID;
    private int duration;
    private int report;
    private String courseImg;
    private String courseName;
    private String Description;
    private double price;
    private double salePrice;
    private boolean isActive;
    private User userId;
   

    public Course() {
    }


    public Course(int courseID, int duration, int report, String courseImg, String courseName, String Description, double price, double salePrice, boolean isActice, User userId) {
        this.courseID = courseID;
        this.duration = duration;
        this.report = report;
        this.courseImg = courseImg;
        this.courseName = courseName;
        this.Description = Description;
        this.price = price;
        this.salePrice = salePrice;
        this.isActive = isActive;
        this.userId = userId;
    }
    

  

    

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", duration=" + duration + ", report=" + report + ", courseImg=" + courseImg + ", courseName=" + courseName + ", Description=" + Description + ", price=" + price + ", salePrice=" + salePrice + ", isActive=" + isActive + ", userId=" + userId + '}';
    }

    

   
   
    
}
