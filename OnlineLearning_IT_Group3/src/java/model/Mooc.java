/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DTC
 */
public class Mooc {
    private int moocId;
    private int moocNumber;
    private String moocName;
    private Course courseId;

    public Mooc() {
    }

    public Mooc(int moocId, int moocNumber, String moocName) {
        this.moocId = moocId;
        this.moocNumber = moocNumber;
        this.moocName = moocName;
    }

    public Mooc(int moocId, int moocNumber, String moocName, Course courseId) {
        this.moocId = moocId;
        this.moocNumber = moocNumber;
        this.moocName = moocName;
        this.courseId = courseId;
    }

    public int getMoocId() {
        return moocId;
    }

    public void setMoocId(int moocId) {
        this.moocId = moocId;
    }

    public int getMoocNumber() {
        return moocNumber;
    }

    public void setMoocNumber(int moocNumber) {
        this.moocNumber = moocNumber;
    }

    public String getMoocName() {
        return moocName;
    }

    public void setMoocName(String moocName) {
        this.moocName = moocName;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Mooc{" + "moocId=" + moocId + ", moocNumber=" + moocNumber + ", moocName=" + moocName + ", courseId=" + courseId + '}';
    }
    
}
