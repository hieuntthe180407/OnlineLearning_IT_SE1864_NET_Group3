/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.*;
import model.Category;
import model.Course;
import model.Price;
/**
 *
 * @author trong
 */
public class CourseDAO extends DBContext {
//        public List<Course> getAllCourse() {
//
//        List<Course> list = new ArrayList<>();
//
//        String sql = "Select * from Course";
//                
//
//        try {
//
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                Course c= new Course();
//                c.setCourseID(rs.getInt("courseID"));
//                c.setDuration(rs.getInt("Duration"));
//                c.setReport(rs.getInt("Report"));
//                c.setCourseImg(rs.getString("courseIMG"));
//                c.setCourseName(rs.getString("courseName"));
//                c.setDescription(rs.getString("Description"));
//                list.add(c);
//                
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return list;
//    }
        public List<Course> getAllCourse() {
    List<Course> list = new ArrayList<>();
    String sql = "SELECT c.CourseID, c.CourseName, c.CourseImg, c.Duration, c.Description, p.ListPrice, p.SalePrice " +
                 "FROM Course c " +
                 "LEFT JOIN Price p ON c.CourseID = p.CourseID"; // Sử dụng LEFT JOIN để lấy tất cả khóa học

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Course c = new Course();
            c.setCourseID(rs.getInt("CourseID"));
            c.setDuration(rs.getInt("Duration"));
            c.setReport(rs.getInt("Report"));
            c.setCourseImg(rs.getString("CourseImg"));
            c.setCourseName(rs.getString("CourseName"));
            c.setDescription(rs.getString("Description"));

            // Tạo đối tượng Price và gán cho course
            Price price = new Price();
            price.setCourseID(c.getCourseID());
            price.setListPrice(rs.getDouble("ListPrice"));
            price.setSalePrice(rs.getDouble("SalePrice"));
            price.setIsActive(true); // Gán giá trị này từ database nếu cần

            c.setPrice(price);  // Gán đối tượng Price cho Course
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
       public List<Course> searchCoursesByName(String searchKeyword) {
    List<Course> courses = new ArrayList<>();
    String sql = "SELECT c.CourseID, c.CourseName, c.CourseImg, c.Duration, c.Description, p.ListPrice, p.SalePrice " +
                 "FROM Course c " +
                 "JOIN Price p ON c.CourseID = p.CourseID " +
                 "WHERE c.CourseName LIKE ? AND p.IsActive = 1"; // Đảm bảo chỉ hiển thị giá đang hoạt động

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, "%" + searchKeyword + "%"); // Thiết lập từ khóa tìm kiếm với dấu `%`
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Course course = new Course();
            course.setCourseID(rs.getInt("CourseID"));
            course.setDuration(rs.getInt("Duration"));
            course.setReport(rs.getInt("Report"));
            course.setCourseImg(rs.getString("CourseImg"));
            course.setCourseName(rs.getString("CourseName"));
            course.setDescription(rs.getString("Description")); // Lấy mô tả từ kết quả

            // Tạo đối tượng Price và gán cho course
            Price price = new Price();
            price.setCourseID(course.getCourseID());
            price.setListPrice(rs.getDouble("ListPrice"));
            price.setSalePrice(rs.getDouble("SalePrice"));
            price.setIsActive(true); // Hoặc gán giá trị từ database nếu có trường IsActive

            course.setPrice(price);  // Gán đối tượng Price cho Course
            courses.add(course);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
}

         
         
          public static void main(String[] args) {
        CourseDAO cDAO = new CourseDAO();
        List<Course> list = cDAO.getAllCourse();
        for (Course u : list) {
            System.out.println(u);
        }
    }
}
