/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author ADMIN
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

public class SliderDAO extends DBContext{
    private Connection conn;

    public SliderDAO() {
        this.conn = connection; // Kết nối đến cơ sở dữ liệu
    }

    /**
     * Lấy tất cả slider đã xuất bản.
     * 
     * @return Danh sách các slider.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */
    public List<Slider> getAllSliders() throws SQLException {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM Slider where Publish = 1";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            sliders.add(slider);
        }
        return sliders; // Trả về danh sách slider
    }
    
        /**
     * Lấy tất cả slider cho quản trị viên.
     * 
     * @return Danh sách các slider.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */

    public List<Slider> getAllSlidersAdmin() throws SQLException {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM Slider";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            sliders.add(slider);
        }
        return sliders; // Trả về danh sách slider
    }

    /**
     * Lấy slider theo ID.
     * 
     * @param id ID của slider cần lấy.
     * @return Đối tượng Slider nếu tìm thấy, ngược lại là null.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */
    public Slider getSliderById(int id) throws SQLException {
        String query = "SELECT * FROM Slider WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            return slider; // Trả về slider tìm thấy
        }
        return null; // Không tìm thấy slider
    }

    
    /**
     * Thêm một slider mới.
     * 
     * @param slider Đối tượng slider cần thêm.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */
    public void addSlider(Slider slider) throws SQLException {
        String query = "INSERT INTO Slider (ImageUrl, Title, BackLink, Description, Publish) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, slider.getImageUrl());
        ps.setString(2, slider.getTitle());
        ps.setString(3, slider.getBackLink());
        ps.setString(4, slider.getDescription());
        ps.setBoolean(5, slider.isPublish());
        ps.executeUpdate(); // Thực hiện thêm slider
    }

    /**
     * Cập nhật thông tin slider.
     * 
     * @param slider Đối tượng slider cần cập nhật.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */
    public void updateSlider(Slider slider) throws SQLException {
        String query = "UPDATE Slider SET ImageUrl = ?, Title = ?, BackLink = ?, Description = ?, Publish = ? WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, slider.getImageUrl());
        ps.setString(2, slider.getTitle());
        ps.setString(3, slider.getBackLink());
        ps.setString(4, slider.getDescription());
        ps.setBoolean(5, slider.isPublish());
        ps.setInt(6, slider.getSliderID());
        ps.executeUpdate(); // Thực hiện cập nhật slider
    }

    /**
     * Xóa một slider theo ID.
     * 
     * @param sliderID ID của slider cần xóa.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn.
     */
    public void deleteSlider(int sliderID) throws SQLException {
        String query = "DELETE FROM Slider WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, sliderID);
        ps.executeUpdate(); // Thực hiện xóa slider
    }
}
