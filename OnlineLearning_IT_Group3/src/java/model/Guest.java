/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trong
 */
public class Guest {
    int GuestID;
    String Address;
    String Phone;
    String preferedPhone;
    String FullName;

    public Guest() {
    }

    public Guest(int GuestID, String Address, String Phone, String preferedPhone, String FullName) {
        this.GuestID = GuestID;
        this.Address = Address;
        this.Phone = Phone;
        this.preferedPhone = preferedPhone;
        this.FullName = FullName;
    }

    public int getGuestID() {
        return GuestID;
    }

    public void setGuestID(int GuestID) {
        this.GuestID = GuestID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPreferedPhone() {
        return preferedPhone;
    }

    public void setPreferedPhone(String preferedPhone) {
        this.preferedPhone = preferedPhone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }
    
    
}
