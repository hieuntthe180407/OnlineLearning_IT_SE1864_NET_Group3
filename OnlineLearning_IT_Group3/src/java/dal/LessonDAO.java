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
               
                
                list.add(u);
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
     public boolean updateLesson(int id, String name,String url,String des,int num){
        try {
            String sql = "update Lessons set LessonName = ?, LessonURL = ?,Description=?, LessonNumber=? where LessonID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, url);
             st.setString(3, des);
            st.setInt(4, num); 
            st.setInt(5, id);  
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
      public boolean addLesson(String name,String url, int MoocID,String des, int num){
        try {
            String sql = "Insert into Lessons(LessonName,LessonURL,MoocID,Description,LessonNumber) values(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, url);
            
               st.setInt(3,MoocID); 
               st.setString(4, des);
               st.setInt(5,num); 
            st.executeUpdate();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
