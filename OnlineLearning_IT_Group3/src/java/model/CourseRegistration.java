/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author laptop acer
 */
public class CourseRegistration {
    private int userId;            // ID người dùng
    private String email;          // Email người dùng
    private int courseId;          // ID khóa học
    private String packageName;    // Tên gói
    private BigDecimal price;       // Giá của khóa học
    private String status;          // Trạng thái đăng ký
    private Timestamp validFrom;         // Ngày bắt đầu hiệu lực
    private Timestamp validTo;           // Ngày kết thúc hiệu lực
    // Phương thức khởi tạo
    public CourseRegistration(int userId, String email, int courseId, String packageName,
                              BigDecimal price, String status, Timestamp validFrom, Timestamp validTo) {
        this.userId = userId;
        this.email = email;
        this.courseId = courseId;
        this.packageName = packageName;
        this.price = price;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        
    }
    User user;
    Course course;
    public CourseRegistration() {
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    // Getter và setter
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }
    public Date getValidTo() {
        return validTo;
    }
    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }
    @Override
    public String toString() {
        return "CourseRegistration{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", courseId=" + courseId +
                ", packageName='" + packageName + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }
}
