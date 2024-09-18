/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trong
 */
public class Mooc {
    private int moocID;
    private String moocName;
    private int moocNumber;

    public Mooc() {
    }

    public Mooc(int moocID, String moocName, int moocNumber) {
        this.moocID = moocID;
        this.moocName = moocName;
        this.moocNumber = moocNumber;
    }

    public int getMoocID() {
        return moocID;
    }

    public void setMoocID(int moocID) {
        this.moocID = moocID;
    }

    public String getMoocName() {
        return moocName;
    }

    public void setMoocName(String moocName) {
        this.moocName = moocName;
    }

    public int getMoocNumber() {
        return moocNumber;
    }

    public void setMoocNumber(int moocNumber) {
        this.moocNumber = moocNumber;
    }
    
    
}
