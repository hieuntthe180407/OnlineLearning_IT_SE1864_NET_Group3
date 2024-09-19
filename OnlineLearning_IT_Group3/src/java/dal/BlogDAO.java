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
import model.Blog;

/**
 *
 * @author ADMIN
 */
public class BlogDAO extends DBContext{
    public List<Blog> getHostPost() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT TOP 6 * FROM Blogs where status='Published'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(
                    rs.getInt("BlogId"),
                    rs.getInt("UserId"),
                    rs.getInt("CategoryId"),
                    rs.getString("Title"),
                    rs.getString("Content"),
                    rs.getString("Status"),
                    rs.getString("FeaturedImage"),
                    rs.getTimestamp("CreatedAt"),
                    rs.getTimestamp("UpdatedAt")
                );
                blogs.add(blog);
            }
        } catch (SQLException e) {
            System.out.println("Error: "  + e);
        }
        return blogs;
    }
    
}
