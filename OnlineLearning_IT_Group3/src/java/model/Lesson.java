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
    private int LessionID;
    private String LessionURL;
     private String LessionName;
      private String Description;

    public Lesson() {
    }

    public Lesson(int LessionID, String LessionURL, String LessionName, String Description) {
        this.LessionID = LessionID;
        this.LessionURL = LessionURL;
        this.LessionName = LessionName;
        this.Description = Description;
    }

    public int getLessionID() {
        return LessionID;
    }

    public void setLessionID(int LessionID) {
        this.LessionID = LessionID;
    }

    public String getLessionURL() {
        return LessionURL;
    }

    public void setLessionURL(String LessionURL) {
        this.LessionURL = LessionURL;
    }

    public String getLessionName() {
        return LessionName;
    }

    public void setLessionName(String LessionName) {
        this.LessionName = LessionName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
      
}
