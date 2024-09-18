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
                u.setLessionID(rs.getInt("LessonID"));
                u.setLessionName(rs.getString("LessonName"));
                u.setDescription(rs.getString("Description"));
                u.setLessionURL(rs.getString("LessonURL"));
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
                u.setLessionID(rs.getInt("LessonID"));
                u.setLessionName(rs.getString("LessonName"));
                u.setDescription(rs.getString("Description"));
                u.setLessionURL(rs.getString("LessonURL"));
               
                
                list.add(u);
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    
}
