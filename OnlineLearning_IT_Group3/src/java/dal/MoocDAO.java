/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Mooc;

/**
 *
 * @author trong
 */
public class MoocDAO extends DBContext {
      public List<Mooc> getAllMoocByCourseID(int courseID) {

        List<Mooc> list = new ArrayList<>();

        try {
            String sql = "Select * from Mooc WHERE CourseID=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Mooc m = new Mooc();
                m.setMoocID(rs.getInt("moocID"));
                m.setMoocName(rs.getString("MoocName"));
                m.setMoocNumber(rs.getInt("MoocNumber"));
                
                list.add(m);
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
