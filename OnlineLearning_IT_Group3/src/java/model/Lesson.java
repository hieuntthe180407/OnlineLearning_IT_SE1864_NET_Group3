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
    private int LessonNumber;
    private String LessonImg;

    public String getLessonImg() {
        return LessonImg;
    }

    public void setLessonImg(String LessonImg) {
        this.LessonImg = LessonImg;
    }
    private String LessonURL;
     private String LessonName;
      private String Description;
      private String Status;

    public Lesson() {
    }

    public Lesson(int LessonID, int MoocID, int LessonNumber, String LessonURL, String LessonName, String Description, String Status) {
        this.LessonID = LessonID;
        this.MoocID = MoocID;
        this.LessonNumber = LessonNumber;
        this.LessonURL = LessonURL;
        this.LessonName = LessonName;
        this.Description = Description;
        this.Status = Status;
    }

    
    

    public int getLessonNumber() {
        return LessonNumber;
    }

    public void setLessonNumber(int LessonNumber) {
        this.LessonNumber = LessonNumber;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Lesson{" + "LessonID=" + LessonID + ", MoocID=" + MoocID + ", LessonNumber=" + LessonNumber + ", LessonImg=" + LessonImg + ", LessonURL=" + LessonURL + ", LessonName=" + LessonName + ", Description=" + Description + ", Status=" + Status + '}';
    }

    
      
}
