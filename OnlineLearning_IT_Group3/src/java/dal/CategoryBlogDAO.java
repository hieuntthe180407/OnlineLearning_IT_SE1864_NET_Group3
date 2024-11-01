/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.util.ArrayList;
import java.util.List;
import model.CategoryBlog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class CategoryBlogDAO extends DBContext{
    
     /**
     * Lấy tất cả các danh mục blog từ cơ sở dữ liệu.
     * 
     * @return Danh sách các đối tượng CategoryBlog.
     */
     public List<CategoryBlog> getAllCategories() {
        List<CategoryBlog> categories = new ArrayList<>();
        String query = "SELECT CategoryId, CategoryName, Description, CreatedAt FROM CategoriesBlog";
        try (
             PreparedStatement pstmt = connection.prepareStatement(query); 
             ResultSet rs = pstmt.executeQuery()) {
            
            // Duyệt qua kết quả truy vấn và thêm các danh mục vào danh sách
            while (rs.next()) {
                CategoryBlog category = new CategoryBlog();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setDescription(rs.getString("Description"));
                category.setCreatedAt(rs.getTimestamp("CreatedAt"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories; // Trả về danh sách các danh mục
    }
}
