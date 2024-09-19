/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import model.Course;

/**
 *
 * @author trong
 */
public class CourseDAO extends DBContext {

    public List<Course> getAllCourse() {

        List<Course> list = new ArrayList<>();

        String sql = "Select * from Course";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseImg(rs.getString("courseIMG"));
                c.setCourseName(rs.getString("courseName"));
                c.setDescription(rs.getString("Description"));
                list.add(c);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public Course getCourseByID(int id) {
        try {
            String sql = "Select * from Course where CourseID = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseName(rs.getString("CourseName"));
                c.setCourseImg(rs.getString("courseIMG"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CourseDAO cDAO = new CourseDAO();
        List<Course> list = cDAO.getAllCourse();
        for (Course u : list) {
            System.out.println(u);
        }
    }

    public List<Course> getFeaturedCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT TOP 6 CourseID, CategoryID, CourseImg, CourseName, Publish, Duration, Report, IsDiscontinued, NewVersionId, Description "
                + "FROM Course WHERE Publish = 1 AND IsDiscontinued = 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseImg(rs.getString("courseIMG"));
                c.setCourseName(rs.getString("courseName"));
                c.setDescription(rs.getString("Description"));
                courses.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return courses;
    }
}
