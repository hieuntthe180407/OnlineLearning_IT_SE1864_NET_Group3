/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Blog;
import model.CategoryBlog;
import model.CourseRegistration;
import model.User;

/**
 *
 * @author laptop acer
 */
public class CourseRegisDao extends DBContext {

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
                UserDAO ud = new UserDAO();
                registration.setUser(ud.getUserProfilebyId(registration.getUserId()));
                CourseDAO cd = new CourseDAO();
                registration.setCourse(cd.getCourseByID(registration.getCourseId()));
                registrations.add(registration);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return registrations;
    }

    public List<CourseRegistration> getMyCourse(int userId) {
        List<CourseRegistration> registrations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Course_Registration WHERE userId = ? and status = 'complete'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId); // Set the email parameter
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
                UserDAO ud = new UserDAO();
                registration.setUser(ud.getUserProfilebyId(registration.getUserId()));
                CourseDAO cd = new CourseDAO();
                registration.setCourse(cd.getCourseByID(registration.getCourseId()));
                registrations.add(registration);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return registrations;
    }

    public void registCourse(User u, String courseId) {
        try {
            String sql = "insert into Course_Registration (userId, email, courseId, status, validFrom, validTo) values (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getUserID()); // Set the userId parameter
            ps.setString(2, u.getEmail()); // Set the email parameter
            ps.setInt(3, Integer.parseInt(courseId)); // Set the courseId parameter
            ps.setString(4, "inprogress"); // Set the status parameter
            // Lấy ngày hiện tại
            Date currentDate = new Date(System.currentTimeMillis());
            ps.setDate(5, currentDate); // Set the validFrom parameter
            // Tính ngày validTo bằng cách thêm 1 năm vào ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.YEAR, 1);
            Date nextYearDate = new Date(calendar.getTimeInMillis());
            ps.setDate(6, nextYearDate); // Set the validTo parameter
            ps.executeUpdate(); // Thực hiện truy vấn
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
