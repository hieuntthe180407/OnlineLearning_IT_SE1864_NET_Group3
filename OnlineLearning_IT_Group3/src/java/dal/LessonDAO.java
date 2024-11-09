/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

import model.Lesson;
/**
 *
 * @author trong
 */
public class LessonDAO extends DBContext {
    public Lesson getLessonByID(int id){
        try {
            String sql = "Select * from Lessons where LessonID = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                Lesson u = new Lesson();
                u.setLessonID(rs.getInt("LessonID"));
                u.setMoocID(rs.getInt("MoocID"));
                u.setLessonName(rs.getString("LessonName"));
                u.setDescription(rs.getString("Description"));
                u.setLessonNumber(rs.getInt("LessonNumber"));
                u.setLessonURL(rs.getString("LessonURL"));
                u.setLessonImg(rs.getString("LessonImg"));
                return u;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<Lesson> getAlllessonBycourseID(int CourseID) {

        List<Lesson> list = new ArrayList<>();

        try {
            String sql = "Select * from Mooc m,Lessons l,Course c WHERE m.CourseID=c.CourseID AND l.MoocID=m.MoocID AND c.CourseID=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CourseID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
              Lesson u = new Lesson();
                u.setLessonID(rs.getInt("LessonID"));
                u.setLessonName(rs.getString("LessonName"));
                 u.setMoocID(rs.getInt("MoocID"));
                u.setDescription(rs.getString("Description"));
                u.setLessonNumber(rs.getInt("LessonNumber"));
                u.setLessonURL(rs.getString("LessonURL"));
                u.setStatus(rs.getString("Status"));
               u.setLessonImg(rs.getString("LessonImg"));
                
                list.add(u);
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    //update lesson
     public boolean updateLesson(int id, String name,String url,String des,int num, String img){
        try {
            String sql = "update Lessons set LessonName = ?, LessonURL = ?,Description=?, LessonNumber=?, LessonImg=? where LessonID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, url);
             st.setString(3, des);
            st.setInt(4, num); 
            st.setString(5, img);
            st.setInt(6, id);  
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
      public boolean addLesson(String name,String url, int MoocID,String des, int num,String img){
        try {
            String sql = "Insert into Lessons(LessonName,LessonURL,MoocID,Description,LessonNumber,LessonImg) values(?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, url);
            
               st.setInt(3,MoocID); 
               st.setString(4, des);
               st.setInt(5,num); 
               st.setString(6, img);
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
       public List<Lesson> getLessons(int offset, int limit,int courseID) {
        List<Lesson> list = new ArrayList<>();

        try {
             String query = "Select * from Mooc m,Lessons l,Course c WHERE m.CourseID=c.CourseID "
                     + "AND l.MoocID=m.MoocID AND c.CourseID=?  ORDER BY LessonID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, courseID);
            st.setInt(2, offset);
            st.setInt(3, limit);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                  Lesson u = new Lesson();
                u.setLessonID(rs.getInt("LessonID"));
                u.setLessonName(rs.getString("LessonName"));
                 u.setMoocID(rs.getInt("MoocID"));
                u.setDescription(rs.getString("Description"));
                u.setLessonNumber(rs.getInt("LessonNumber"));
                u.setLessonURL(rs.getString("LessonURL"));
               u.setStatus(rs.getString("Status"));
                u.setLessonImg(rs.getString("LessonImg"));
                list.add(u);

              
               
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
       public int getTotalLessonCount(int courseID) {
        
       try {
            String sql =  "SELECT COUNT(*) from Mooc m,Lessons l,Course c WHERE m.CourseID=c.CourseID AND l.MoocID=m.MoocID AND c.CourseID=?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, courseID);
           
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 in case of any error
    }
       
        public boolean activeLessonStatus(int id,String active){
        try {
            String sql = "update Lessons set Status=? where LessonID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, active);
            st.setInt(2, id);  
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
         public boolean deactiveLessonStatus(int id,String deactive){
        try {
            String sql = "update Lessons set Status=? where LessonID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, deactive);
            st.setInt(2, id);  
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
        LessonDAO l = new LessonDAO();
        l.addLesson("a", "a", 2, "no", 1, "test");
//           List<Lesson> list = l.getLessons(0, 3, 2);
//           for(Lesson d :list)
//           {
//               System.out.println(d);
//           }
       
       
        }
    }

