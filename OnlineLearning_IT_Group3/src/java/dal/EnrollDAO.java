/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;

/**
 *
 * @author trong
 */
public class EnrollDAO extends DBContext {
    public void addEnrollUser(int uID, int cID) {
        try {
            String sql = "Insert into Enroll(UserID,CourseID) values(?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uID);
            st.setInt(2, cID);

            st.executeUpdate();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
   public boolean Enrolled(int uid, int cid) {
    try {
        String sql = "SELECT COUNT(*) FROM ENROLL WHERE UserID=? AND CourseID=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, uid);
        st.setInt(2, cid);

        // Execute the query
        ResultSet rs = st.executeQuery();

        // Check if there is a result
        if (rs.next()) {
            // If count > 0, the user is enrolled
            return rs.getInt(1) > 0;
        }

        // Close resources
        rs.close();
        st.close();
        
        return false; // No results found
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    }
}
    public static void main(String[] args) {
        EnrollDAO e = new EnrollDAO();
        if(e.Enrolled(1, 3))
        {
            System.out.println("ok");
        }
        else System.out.println("not ok");
    }
}
