/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 *
 * @author trong
 */
public class UserDAO extends DBContext {
    public Map<Integer, User> getAllUser(){
        Map<Integer, User> list = new HashMap<>();
        try {
            String sql = "Select * from [dbo].[User]";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                 u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setAddress(rs.getString("Address"));
                u.setRoleID(rs.getInt("RoleID"));
                list.put(u.getUserID(), u);
            }
            rs.close();
            st.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public Map<Integer, User> searchUser(String str) {
    Map<Integer, User> list = new HashMap<>();
    try {
     
        String sql = "SELECT * FROM [dbo].[User] WHERE FullName LIKE ? OR Phone LIKE ? OR Email LIKE ?";
        PreparedStatement st = connection.prepareStatement(sql);
        
    
        String searchTerm = "%" + str + "%";
        
       
        st.setString(1, searchTerm);
        st.setString(2, searchTerm);
        st.setString(3, searchTerm);
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setAddress(rs.getString("Address"));
                u.setRoleID(rs.getInt("RoleID"));
            list.put(u.getUserID(), u);
        }
        rs.close();
        st.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return list;
}

    public Map<Integer, User> filterUser(String gender, String role, String status ){
        Map<Integer, User> list = new HashMap<>();
        try {
            String sql = "SELECT u.*, r.roleName FROM [dbo].[User] AS u LEFT JOIN Role AS r ON u.roleID = r.roleID WHERE Gender LIKE ? AND roleName LIKE ? AND isVerify LIKE ?";
                  
            PreparedStatement st = connection.prepareStatement(sql);
            
            
            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
               User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setAddress(rs.getString("Address"));
                u.setRoleName(rs.getString("RoleName"));
                u.setRoleID(rs.getInt("RoleID"));
                list.put(u.getUserID(), u);
            }
            rs.close();
            st.close();            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
     public static void main(String[] args) {
        UserDAO cDAO = new UserDAO();
        Map<Integer, User> list = cDAO.filterUser("Male","","");
          for(User u: list.values()){
              System.out.println(u);
          }
        }
}
