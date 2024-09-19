/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

/**
 *
 * @author ADMIN
 */
public class SliderDAO extends DBContext{
    public List<Slider> getAllSliders() {
        List<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Slider WHERE Publish = 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider(rs.getInt("SliderID"), rs.getString("ImageUrl"), rs.getString("Title"), rs.getString("BackLink"), true);
                sliders.add(slider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sliders;
    }
}
