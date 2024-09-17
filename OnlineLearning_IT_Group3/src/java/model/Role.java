/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author ADMIN
 */
import java.util.*;
import java.lang.*;
public class Role {
    private int roleId;
    private String roleName;

    public Role() {
    }    

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }
    
    

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    

}
