/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Course;

import model.Review;
import model.User;

/**
 *
 * @author DTC
 */
public class ReviewDAO extends  DBContext{
    
   public  List<Review> getReviewByCourseId(int courseId){
       List<Review> reviews = new ArrayList<>();
       
       String sql ="SELECT [ReviewID], [UserID], [CourseID],[Rating], [Time],[IsReport] "
            +"FROM [dbo].[Review]"
            +"WHERE [CourseID] = ?";
       
      
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, courseId);
           ResultSet rs = preparedStatement.executeQuery();
           
           while (rs.next()) {               
               Review review = new  Review();
               
               review.setReviewID(rs.getInt("ReviewID"));
               
                // Assuming User and Course objects need to be fetched or created
                // You'll need to implement a method to retrieve a User by ID
                User user = new User();
                user.setUserID(rs.getInt("UserID")); // Assuming User has a setUserID method
                review.setUserID(user); // Set the user object

                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID")); // Assuming Course has a setCourseID method
                review.setCourseID(course); // Set the course object

                review.setRating(rs.getInt("Rating"));
                review.setTime(rs.getTimestamp("Time").toLocalDateTime()); // Convert to LocalDateTime
                review.setReviewContent(rs.getString("ReviewContent"));
                review.setIsReport(rs.getInt("IsReport"));

                reviews.add(review);
           }
           
       } catch (Exception e) {
           
           System.out.println("Error retrieving reviews: "+e.getMessage());
       }
       
      return reviews;
   }
   public void addReview(int cID,String content, int uID)
   {
       try {
            String sql = "Insert into Review(CourseID,UserID,ReviewContent) values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cID);
            st.setInt(2, uID);
            
              
               st.setString(3, content);
                
            st.executeUpdate();
            st.close();
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            
        }
   }
   
   
   
    public static void main(String[] args) {
        ReviewDAO rDAO = new ReviewDAO();
        List<Review> r = rDAO.getReviewByCourseId(3);
        for (Review re : r)
        {
            System.out.println(re);
        }
    }
   
}

    
    
   
