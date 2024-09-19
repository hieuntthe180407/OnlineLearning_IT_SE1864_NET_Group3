/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.CategoryBlog;

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
    public List<Blog> getBlogs(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM Blogs ORDER BY UpdatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setTitle(rs.getString("Title"));
                blog.setContent(rs.getString("Content"));
                blog.setFeaturedImage(rs.getString("FeaturedImage"));
                blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    public int getTotalBlogCount() {
        String query = "SELECT COUNT(*) FROM Blogs";
        try (
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<CategoryBlog> getCategories() {
        List<CategoryBlog> categories = new ArrayList<>();
        String query = "SELECT * FROM CategoriesBlog";
        try (
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                CategoryBlog category = new CategoryBlog();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    public List<Blog> getLatestBlogs(int count) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT TOP (?) * FROM Blogs ORDER BY UpdatedAt DESC";
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, count);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setTitle(rs.getString("Title"));
                blog.setFeaturedImage(rs.getString("FeaturedImage"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
}
