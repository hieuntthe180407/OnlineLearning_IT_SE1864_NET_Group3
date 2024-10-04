package model;

public class Price {
    private int PriceID;
    private int CourseID;
    private double ListPrice;
    private double SalePrice;
    private boolean IsActive;

    // Constructor không đối số
    public Price() {
    }

    // Constructor với đối số
    public Price(int priceID, int courseID, double listPrice, double salePrice, boolean isActive) {
        this.PriceID = priceID;
        this.CourseID = courseID;
        this.ListPrice = listPrice;
        this.SalePrice = salePrice;
        this.IsActive = isActive;
    }

    // Getter và Setter
    public int getPriceID() {
        return PriceID;
    }

    public void setPriceID(int priceID) {
        this.PriceID = priceID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        this.CourseID = courseID;
    }

    public double getListPrice() {
        return ListPrice;
    }

    public void setListPrice(double listPrice) {
        this.ListPrice = listPrice;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        this.SalePrice = salePrice;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean isActive) {
        this.IsActive = isActive;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Price{" +
                "PriceID=" + PriceID +
                ", CourseID=" + CourseID +
                ", ListPrice=" + ListPrice +
                ", SalePrice=" + SalePrice +
                ", IsActive=" + IsActive +
                '}';
    }

    public void setActive(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
