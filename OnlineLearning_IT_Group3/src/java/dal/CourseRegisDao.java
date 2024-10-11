/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.CategoryBlog;
import model.CourseRegistration;
/**
 *
 * @author laptop acer
 */
public class CourseRegisDao extends DBContext{
    public List<CourseRegistration> getCourseRegistrationsByEmail(String email) {
        List<CourseRegistration> registrations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Course_Registration WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email); // Set the email parameter
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CourseRegistration registration = new CourseRegistration(
                    rs.getInt("userId"),
                    rs.getString("email"),
                    rs.getInt("courseId"),
                    rs.getString("package"),
                    rs.getBigDecimal("price"),
                    rs.getString("status"),
                    rs.getTimestamp("validFrom"),
                    rs.getTimestamp("validTo")
                );
                registrations.add(registration);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return registrations;
    }
    
}
