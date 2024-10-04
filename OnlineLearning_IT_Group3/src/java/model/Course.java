package model;

public class Course {
    private int courseID;
    private int duration;
    private int report;
    private String courseImg;
    private String courseName;
    private String description;
    private Price price;  // Thêm thuộc tính Price

    // Constructor không đối số
    public Course() {
    }

    // Constructor với đối số
    public Course(int courseID, int duration, int report, String courseImg, String courseName, String description, Price price) {
        this.courseID = courseID;
        this.duration = duration;
        this.report = report;
        this.courseImg = courseImg;
        this.courseName = courseName;
        this.description = description;
        this.price = price;  // Gán giá cho thuộc tính Price
    }

    // Getter và Setter cho các thuộc tính
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", duration=" + duration +
                ", report=" + report +
                ", courseImg='" + courseImg + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
