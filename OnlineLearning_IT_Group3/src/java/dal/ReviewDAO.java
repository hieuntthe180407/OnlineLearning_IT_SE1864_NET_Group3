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
 * @author trong
 */
public class ReviewDAO extends DBContext {
    public List<Review> getAllReview() {

        List<Review> list = new ArrayList<>();

        String sql = "Select * from Review";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setReviewID(rs.getInt("ReviewID"));
                r.setReviewContent(rs.getString("ReviewContent"));
                

                User u= new User();
                u.setUserID(rs.getInt("UserID"));
                r.setUser(u);
                
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                r.setCourse(c);
                list.add(r);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    
     public boolean addReview(int uID, int cID, String content){
        try {
            String sql = "Insert into Review(UserID,CourseID,ReviewContent) values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
           
            
               st.setInt(1,uID); 
               st.setInt(2, cID);
               st.setString(3,content); 
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
     
     public static void main(String[] args) {
        ReviewDAO r = new ReviewDAO();
        List<Review> list = r.getAllReview();
        
        for (Review u : list) {
            System.out.println(u);
        }
    }
}
