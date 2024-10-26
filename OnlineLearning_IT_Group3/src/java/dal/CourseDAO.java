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



import model.User;

/**
 *
 * @author trong
 */
public class CourseDAO extends DBContext {


//lấy tất cả danh sách các course
       public List<Course> getAllCourse() {


        List<Course> list = new ArrayList<>();

        String sql = "  SELECT \n"
                + "    c.[CourseID],\n"
                + "    c.[UserID],\n"
                + "    c.[CategoryID],\n"
                + "    c.[CourseImg],\n"
                + "    c.[CourseName],\n"
                + "    c.[Publish],\n"
                + "    c.[Duration],\n"
                + "    c.[Report],\n"
                + "    c.[IsDiscontinued],\n"
                + "    c.[NewVersionId],\n"
                + "    c.[Description],\n"
                + "    p.[ListPrice],\n"
                + "    p.[SalePrice],\n"
                + "    p.[IsActive]\n"
                + "FROM \n"
                + "    [dbo].[Course] c\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "         [PriceID],\n"
                + "         [CourseID],\n"
                + "         [ListPrice],\n"
                + "         [SalePrice],\n"
                + "         [IsActive]\n"
                + "     FROM \n"
                + "         [dbo].[Price] p1\n"
                + "     WHERE \n"
                + "         [PriceID] = (SELECT MAX([PriceID]) \n"
                + "                      FROM [dbo].[Price] p2 \n"
                + "                      WHERE p2.[CourseID] = p1.[CourseID])\n"
                + "    ) p ON c.[CourseID] = p.[CourseID]";

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
                c.setPrice(rs.getDouble("ListPrice"));
                c.setSalePrice(rs.getDouble("SalePrice"));
                c.setIsActive(rs.getBoolean("IsActive"));
                list.add(c);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
//Lấy thông tin course qua Id
    public Course getCourseByID(int id) {
        try {
            String sql = "  SELECT \n"
                    + "    c.[CourseID],\n"
                    
                    + "    c.[CategoryID],\n"
                    + "    c.[CourseImg],\n"
                    + "    c.[CourseName],\n"
                    + "    c.[Publish],\n"
                    + "    c.[Duration],\n"
                    + "    c.[Report],\n"
                    + "    c.[IsDiscontinued],\n"
                    + "    c.[NewVersionId],\n"
                    + "    c.[Description],\n"
                    + "    p.[ListPrice],\n"
                    + "    p.[SalePrice],\n"
                    + "    p.[IsActive]\n"
                    + "FROM \n"
                    + "    [dbo].[Course] c\n"
                    + "LEFT JOIN \n"
                    + "    (SELECT \n"
                    + "         [PriceID],\n"
                    + "         [CourseID],\n"
                    + "         [ListPrice],\n"
                    + "         [SalePrice],\n"
                    + "         [IsActive]\n"
                    + "     FROM \n"
                    + "         [dbo].[Price] p1\n"
                    + "     WHERE \n"
                    + "         [PriceID] = (SELECT MAX([PriceID]) \n"
                    + "                      FROM [dbo].[Price] p2 \n"
                    + "                      WHERE p2.[CourseID] = p1.[CourseID])\n"
                    + "    ) p ON c.[CourseID] = p.[CourseID] where c.CourseID = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseName(rs.getString("CourseName"));
                c.setCourseImg(rs.getString("courseIMG"));
                c.setPrice(rs.getDouble("ListPrice"));
                c.setSalePrice(rs.getDouble("SalePrice"));
                c.setIsActive(rs.getBoolean("IsActive"));
                c.setDescription(rs.getString("Description"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public List<Category> getTop10Category() {
//        List<Category> categories = new ArrayList<>();
//        String sql = "SELECT TOP 10 CategoryID, CategoryName FROM Category";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Category category = new Category();
//                category.setCategoryID(rs.getInt("CategoryID"));
//                category.setCategoryName(rs.getString("CategoryName"));
//                categories.add(category);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return categories;
//    }
// lấy top 8 category
    public List<Category> getTop8Category() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT TOP 8 * FROM Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setImage(rs.getString("Image"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return categories;
    }
//lấy tất quả các course thông qua việc tìm kiếm
    public List<Course> getAllCourseBySearch(String text) {
        List<Course> list = new ArrayList<>();
        // SQL query with a LIKE clause for partial matching
        String sql = "  SELECT \n"
                + "    c.[CourseID],\n"
                + "    c.[UserID],\n"
                + "    c.[CategoryID],\n"
                + "    c.[CourseImg],\n"
                + "    c.[CourseName],\n"
                + "    c.[Publish],\n"
                + "    c.[Duration],\n"
                + "    c.[Report],\n"
                + "    c.[IsDiscontinued],\n"
                + "    c.[NewVersionId],\n"
                + "    c.[Description],\n"
                + "    p.[ListPrice],\n"
                + "    p.[SalePrice],\n"
                + "    p.[IsActive]\n"
                + "FROM \n"
                + "    [dbo].[Course] c\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "         [PriceID],\n"
                + "         [CourseID],\n"
                + "         [ListPrice],\n"
                + "         [SalePrice],\n"
                + "         [IsActive]\n"
                + "     FROM \n"
                + "         [dbo].[Price] p1\n"
                + "     WHERE \n"
                + "         [PriceID] = (SELECT MAX([PriceID]) \n"
                + "                      FROM [dbo].[Price] p2 \n"
                + "                      WHERE p2.[CourseID] = p1.[CourseID])\n"
                + "    ) p ON c.[CourseID] = p.[CourseID] WHERE courseName LIKE ? OR Description LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set the search parameter with wildcards for partial matching
            st.setString(1, "%" + text + "%");
            st.setString(2, "%" + text + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setDescription(rs.getString("Description"));
                c.setReport(rs.getInt("Report"));
                c.setCourseImg(rs.getString("courseIMG"));
                c.setCourseName(rs.getString("courseName"));
                c.setDescription(rs.getString("Description"));
                c.setPrice(rs.getDouble("ListPrice"));
                c.setSalePrice(rs.getDouble("SalePrice"));
                c.setIsActive(rs.getBoolean("IsActive"));
                list.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
//lấy id của category
    public int getCategoryIdByName(String name) {
        int categoryId = -1;
        String sql = "SELECT CategoryID FROM Category WHERE CategoryName = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                categoryId = rs.getInt("CategoryID");
            }
        } catch (SQLException e) {
            System.out.println("Error while getting CategoryID: " + e.getMessage());
        }

        return categoryId;
    }
//lấy tất cả các course qua categoryId
    public List<Course> getAllCoursesByCategory(String name) {
        List<Course> courses = new ArrayList<>();
        int categoryId = getCategoryIdByName(name);

        // Check if the category ID is valid
        if (categoryId == -1) {
            return courses; // Return an empty list if the category is not found
        }

        String sql = "  SELECT \n"
                + "    c.[CourseID],\n"
                + "    c.[UserID],\n"
                + "    c.[CategoryID],\n"
                + "    c.[CourseImg],\n"
                + "    c.[CourseName],\n"
                + "    c.[Publish],\n"
                + "    c.[Duration],\n"
                + "    c.[Report],\n"
                + "    c.[IsDiscontinued],\n"
                + "    c.[NewVersionId],\n"
                + "    c.[Description],\n"
                + "    p.[ListPrice],\n"
                + "    p.[SalePrice],\n"
                + "    p.[IsActive]\n"
                + "FROM \n"
                + "    [dbo].[Course] c\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "         [PriceID],\n"
                + "         [CourseID],\n"
                + "         [ListPrice],\n"
                + "         [SalePrice],\n"
                + "         [IsActive]\n"
                + "     FROM \n"
                + "         [dbo].[Price] p1\n"
                + "     WHERE \n"
                + "         [PriceID] = (SELECT MAX([PriceID]) \n"
                + "                      FROM [dbo].[Price] p2 \n"
                + "                      WHERE p2.[CourseID] = p1.[CourseID])\n"
                + "    ) p ON c.[CourseID] = p.[CourseID] WHERE CategoryID = ?"; // Assuming there's a CategoryID column in Course

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId); // Set the CategoryID parameter
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setDuration(rs.getInt("Duration"));
                course.setReport(rs.getInt("Report"));
                course.setCourseImg(rs.getString("courseIMG"));
                course.setCourseName(rs.getString("courseName"));
                course.setDescription(rs.getString("Description"));
                course.setPrice(rs.getDouble("ListPrice"));
                course.setSalePrice(rs.getDouble("SalePrice"));
                course.setIsActive(rs.getBoolean("IsActive"));
                courses.add(course); // Add the course to the list
            }
        } catch (SQLException e) {
            System.out.println("Error while getting courses by category: " + e.getMessage());
        }

        return courses; // Return the list of courses
    }
//lấy giá trị lớn nhất của course
    public double getMaxPrice() {
        double maxPrice = 0;
        String sql = "SELECT MAX(ListPrice) AS MaxPrice FROM [SWP391_FALL2024].[dbo].[Price]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                maxPrice = rs.getDouble("MaxPrice");
            }
        } catch (SQLException e) {
            System.out.println("Error while getting max price: " + e.getMessage());
        }

        return maxPrice;
    }
//lấy giá trị nhỏ nhất của course
    public double getMinPrice() {
        double minPrice = 0;
        String sql = "SELECT MIN(ListPrice) AS MinPrice FROM [SWP391_FALL2024].[dbo].[Price]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                minPrice = rs.getDouble("MinPrice");
            }
        } catch (SQLException e) {
            System.out.println("Error while getting minimum price: " + e.getMessage());
        }

        return minPrice;
    }
//lấy danh sách course qua việc search về giá
    public List<Course> getCourseByMinMaxPrice(double minPrice, double maxPrice) {
        List<Course> courses = new ArrayList<>();

        String sql = "  SELECT \n"
                + "    c.[CourseID],\n"
                + "    c.[UserID],\n"
                + "    c.[CategoryID],\n"
                + "    c.[CourseImg],\n"
                + "    c.[CourseName],\n"
                + "    c.[Publish],\n"
                + "    c.[Duration],\n"
                + "    c.[Report],\n"
                + "    c.[IsDiscontinued],\n"
                + "    c.[NewVersionId],\n"
                + "    c.[Description],\n"
                + "    p.[ListPrice],\n"
                + "    p.[SalePrice],\n"
                + "    p.[IsActive]\n"
                + "FROM \n"
                + "    [dbo].[Course] c\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "         [PriceID],\n"
                + "         [CourseID],\n"
                + "         [ListPrice],\n"
                + "         [SalePrice],\n"
                + "         [IsActive]\n"
                + "     FROM \n"
                + "         [dbo].[Price] p1\n"
                + "     WHERE \n"
                + "         [PriceID] = (SELECT MAX([PriceID]) \n"
                + "                      FROM [dbo].[Price] p2 \n"
                + "                      WHERE p2.[CourseID] = p1.[CourseID])\n"
                + "    ) p ON c.[CourseID] = p.[CourseID]\n"
                + "WHERE p.[ListPrice] BETWEEN ? AND ?";  // Filter by price range

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, minPrice);  // Set minimum price
            st.setDouble(2, maxPrice);  // Set maximum price
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setDuration(rs.getInt("Duration"));
                c.setReport(rs.getInt("Report"));
                c.setCourseImg(rs.getString("CourseImg"));
                c.setCourseName(rs.getString("CourseName"));
                c.setDescription(rs.getString("Description"));
                c.setPrice(rs.getDouble("ListPrice"));
                c.setSalePrice(rs.getDouble("SalePrice"));
                c.setIsActive(rs.getBoolean("IsActive"));
                courses.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching courses by price range: " + e.getMessage());
        }

        return courses;
    }
    
    public boolean updateCourse(int id,int Category, String name,String des){
         String sql = "update Course set CategoryID=? , courseName = ?, Description=?  where CourseID = ?";
         try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Category);
            st.setString(2, name);
             st.setString(3, des);
            st.setInt(4, id);  
            st.executeUpdate();
            st.close();
            return true;
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
     public boolean addCourse(String name, int CategoryID,String des, String img){
        try {
            String sql = "Insert into Course(CourseName,CategoryID,Description,CourseImg) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, CategoryID);
            st.setString(4, "img/Course/"+img);
            
               st.setString(3,des); 
              
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
//lấy danh sách course của teacher qua teacher id
    public List<Course> getCoursesByTeacher(int teacherId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.[CourseID], c.[UserID], c.[CategoryID], c.[CourseImg], c.[CourseName], "
                + "c.[Publish], c.[Duration], c.[Report], c.[IsDiscontinued], c.[NewVersionId], "
                + "c.[Description], p.[ListPrice], p.[SalePrice], p.[IsActive] "
                + "FROM [dbo].[Course] c "
                + "LEFT JOIN (SELECT [PriceID], [CourseID], [ListPrice], [SalePrice], [IsActive] "
                + "            FROM [dbo].[Price] p1 "
                + "            WHERE [PriceID] = (SELECT MAX([PriceID]) FROM [dbo].[Price] p2 "
                + "                             WHERE p2.[CourseID] = p1.[CourseID]) ) p "
                + "ON c.[CourseID] = p.[CourseID] WHERE c.UserID = ?"; // Adjust as necessary for your schema

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, teacherId); // Set the teacher's UserID parameter
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("courseID"));
                    
                    UserDAO uDao = new UserDAO();
                    User user = uDao.getUserById(teacherId);
                    c.setUserId(user);
                    
                    
                    c.setDuration(rs.getInt("Duration"));
                    c.setReport(rs.getInt("Report"));
                    c.setCourseImg(rs.getString("courseIMG"));
                    c.setCourseName(rs.getString("courseName"));
                    c.setDescription(rs.getString("Description"));
                    c.setPrice(rs.getDouble("ListPrice"));
                    c.setSalePrice(rs.getDouble("SalePrice"));
                    c.setIsActive(rs.getBoolean("IsActive"));
                    courses.add(c);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching courses by teacher ID: " + e.getMessage());
        }

        return courses;
    }

    public static void main(String[] args) {
        CourseDAO dao = new CourseDAO();
  List<Course> courses =dao.getCoursesByTeacher(1);
          
          for (Course c : courses) {
            System.out.println(c.getUserId().getFullName());
           
            System.out.println("------------------");
        }
        
        dao.addCourse("bruh", 1, "npthing", "course1.jpg");
    }

    public boolean checkCourseByName(String courseName) {
        boolean exists = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM [Course] WHERE CourseName = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, courseName);
            rs = st.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exists;
    }

    public int courseIdByCourseName(String courseName) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int courseId = 0;

        try {
            String sql = "SELECT CourseID FROM Course WHERE CourseName = ?";
            st = connection.prepareStatement(sql);

            st.setString(1, courseName);
            rs = st.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("CourseID");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
        }
        return courseId;
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


