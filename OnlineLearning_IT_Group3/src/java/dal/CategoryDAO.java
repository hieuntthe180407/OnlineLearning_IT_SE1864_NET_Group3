/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.*;
import model.*;
/**
 *
 * @author trong
 */
public class CategoryDAO extends DBContext {
    public List<Category> getAllCategory() {

        List<Category> list = new ArrayList<>();

        String sql = "Select * FROM Category";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                list.add(c);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
