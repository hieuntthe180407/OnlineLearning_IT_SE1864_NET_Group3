/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Enroll;

/**
 *
 * @author trong
 */
public class EnrollDAO extends DBContext {
    // insert UserID va CourseID vao bang Enroll de luu course ma student da enroll
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
   public List<Enroll> getEnroll(int uId)
   {
       List<Enroll> list = new ArrayList<>();
       String sql = "Select * FROM Enroll e, Course c, User u WHERE u.UserID= e.UserID, c.CourseID=e.CourseID AND u.UserID= "+uId;

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Enroll e = new Enroll();
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseImg(rs.getString("courseIMG"));
                c.setCourseName(rs.getString("courseName"));
                c.setDescription(rs.getString("Description"));
                c.setPrice(rs.getDouble("ListPrice"));
                c.setSalePrice(rs.getDouble("SalePrice"));
                c.setIsActive(rs.getBoolean("IsActive"));
                e.setCourse(c);
                list.add(e);
                

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
   }
    public static void main(String[] args) {
        EnrollDAO e = new EnrollDAO();
        System.out.println("");
    }
}
