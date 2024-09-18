/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trong
 */
public class Lesson {
    private int LessonID;
    private int MoocID;
    private String LessonURL;
     private String LessonName;
      private String Description;

    public Lesson() {
    }

    public Lesson(int LessonID, int MoocID, String LessonURL, String LessonName, String Description) {
        this.LessonID = LessonID;
        this.MoocID = MoocID;
        this.LessonURL = LessonURL;
        this.LessonName = LessonName;
        this.Description = Description;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }

    public int getMoocID() {
        return MoocID;
    }

    public void setMoocID(int MoocID) {
        this.MoocID = MoocID;
    }

    public String getLessonURL() {
        return LessonURL;
    }

    public void setLessonURL(String LessonURL) {
        this.LessonURL = LessonURL;
    }

    public String getLessonName() {
        return LessonName;
    }

    public void setLessonName(String LessonName) {
        this.LessonName = LessonName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
      
}
