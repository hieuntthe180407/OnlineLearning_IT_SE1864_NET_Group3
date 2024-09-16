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
            String sql = "Select * from User";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
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
    
    public Map<Integer, User> searchUser(String str){
        Map<Integer, User> list = new HashMap<>();
        try {
            String sql = "SELECT * FROM User WHERE FullName LIKE '%?%' OR Phone LIKE '%?%' OR Email LIKE '%?%'" ;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, str);
            st.setString(2, str);
            st.setString(3, str);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
               User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
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
    
}
