/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.*;
import model.Category;
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
                Course c= new Course();
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
        
         public Course getCourseByID(int id){
        try {
            String sql = "Select * from Course where CourseID = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setDescription(rs.getString("Description"));
                c.setCourseName(rs.getString("CourseName"));
                c.setCourseImg(rs.getString("courseIMG"));
                return c;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
         
         public List<Category> getTop8Category() {
             List<Category> categories = new ArrayList<>();
             
             String sql="SELECT TOP 8 CategoryID, CategoryName FROM Category";
             
             try {
                 PreparedStatement st = connection.prepareCall(sql);
                 ResultSet rs = st.executeQuery();
                 
                 while (rs.next()) {                     
                     
                     Category category = new Category();
                     
                     category.setCategoryID(rs.getInt("CategoryID"));
                     category.setCategoryName(rs.getString("CategoryName"));
                     categories.add(category);
                 }
             } catch (Exception e) {
                 System.out.println(e);
             }
             return categories;
         }
         
        public List<Category> getTop10Category() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT TOP 10 CategoryID, CategoryName FROM Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return categories;
    }
        public List<Course> searchCourseByName(String courseName) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE CourseName LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + courseName + "%"); // Thêm ký tự % để tìm kiếm phần
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
         
         
          public static void main(String[] args) {
        CourseDAO cDAO = new CourseDAO();
        List<Course> list = cDAO.getAllCourse();
        for (Course u : list) {
            System.out.println(u);
        }
    }
}
