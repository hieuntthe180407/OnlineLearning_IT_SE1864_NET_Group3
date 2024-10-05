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


         public Course getCourseByID(int id) {
        try {
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
                    + "    ) p ON c.[CourseID] = p.[CourseID] where CourseID = " + id;
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
}
