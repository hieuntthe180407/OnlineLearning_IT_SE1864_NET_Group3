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
public class ReviewDAO extends DBContext {
// lay toan bo review theo courseId
    public List<Review> getReviewByCourseId(int courseId) {
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT * "
                + "FROM [dbo].[Review] r, [dbo].[User] u "
                + "WHERE r.UserID= u.UserID AND [CourseID] = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, courseId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Review review = new Review();

                review.setReviewID(rs.getInt("ReviewID"));

                // Assuming User and Course objects need to be fetched or created
                // You'll need to implement a method to retrieve a User by ID
                User user = new User();
                user.setUserID(rs.getInt("UserID")); // Assuming User has a setUserID method
                user.setFullName(rs.getString("FullName"));
                user.setAvatar(rs.getString("Avatar"));
                review.setUserID(user); // Set the user object

                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID")); // Assuming Course has a setCourseID method
                review.setCourseID(course); // Set the course object

                review.setRating(rs.getInt("Rating"));
                review.setTime(rs.getTimestamp("Time").toLocalDateTime()); // Convert to LocalDateTime
                review.setReviewContent(rs.getString("ReviewContent"));
               

                reviews.add(review);
            }

        } catch (Exception e) {

            System.out.println("Error retrieving reviews: " + e.getMessage());
        }

        return reviews;
    }
// insert review duoc nhap boi user
    public void addReview(int cID, String content, int uID, int rating) {
        try {
            String sql = "Insert into Review(CourseID,UserID,ReviewContent,Rating) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cID);
            st.setInt(2, uID);

            st.setString(3, content);
            st.setInt(4, rating);
            st.executeUpdate();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void main(String[] args) {
        ReviewDAO rDAO = new ReviewDAO();
        List<Review> r = rDAO.getReviewByCourseId(2);
        for (Review re : r) {
            System.out.println(re);
        }
    }

}
