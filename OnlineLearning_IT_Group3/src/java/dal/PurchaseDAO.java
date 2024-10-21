/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.time.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author trong
 */
public class PurchaseDAO extends DBContext {

    public void addPurchaseGuest(int PriceID, String prePhone, String email, String name, String address, List<String> phones) {
    String insertGuestSql = "INSERT INTO Guest (PreferredPhone, Email, FullName, Address) VALUES (?, ?, ?, ?)";
    String insertPurchaseSql = "INSERT INTO Purchase (GuestId, PriceID) VALUES (?, ?)";
    String insertPhoneSql = "INSERT INTO GuestPhone (GuestId, PhoneNum) VALUES (?, ?)";
    
    try {
        connection.setAutoCommit(false); // Start transaction

        // Insert into Guest
        try (PreparedStatement guestStatement = connection.prepareStatement(insertGuestSql, Statement.RETURN_GENERATED_KEYS)) {
            guestStatement.setString(1, prePhone);
            guestStatement.setString(2, email);
            guestStatement.setString(3, name);
            guestStatement.setString(4, address);
            guestStatement.executeUpdate();

            // Get generated GuestId
            ResultSet generatedKeys = guestStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int guestId = generatedKeys.getInt(1);

                // Insert into Purchase
                try (PreparedStatement purchaseStatement = connection.prepareStatement(insertPurchaseSql)) {
                    purchaseStatement.setInt(1, guestId);
                    purchaseStatement.setInt(2, PriceID);
                    purchaseStatement.executeUpdate();
                }

                // Insert phone numbers
                if (phones != null) {
                    for (String phone : phones) {
                        if (phone != null && !phone.isEmpty()) {
                            try (PreparedStatement phoneStatement = connection.prepareStatement(insertPhoneSql)) {
                                phoneStatement.setInt(1, guestId);
                                phoneStatement.setString(2, phone);
                                phoneStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
        
        connection.commit(); // Commit transaction
    } catch (Exception e) {
        try {
            connection.rollback(); // Rollback transaction on error
        } catch (SQLException rollbackEx) {
            System.out.println("Rollback failed: " + rollbackEx.getMessage());
        }
        System.out.println("Error adding purchase guest: " + e.getMessage());
    } finally {
        try {
            connection.setAutoCommit(true); // Restore default auto-commit behavior
        } catch (SQLException ex) {
            System.out.println("Failed to reset auto-commit: " + ex.getMessage());
        }
    }
}


    public void addPurchaseUser(int uID, int PriceID) {
        try {
            String sql = "Insert into Purchase(UserID,PriceID) values(?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uID);
            st.setInt(2, PriceID);

            st.executeUpdate();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public int getPriceIDbyCourseID(int cID) {
        int priceID = -1; // Use -1 or another default value to indicate "not found"

        try {
            String sql = "SELECT p.PriceID "
                    + "FROM Price p, Course c "
                    + "WHERE p.CourseID = c.CourseID AND c.CourseID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cID); // Set the CourseID parameter

            ResultSet rs = st.executeQuery(); // Use executeQuery for SELECT statements

            if (rs.next()) {
                priceID = rs.getInt("PriceID"); // Get the PriceID from the result set
            }

            rs.close(); // Close the ResultSet
            st.close(); // Close the PreparedStatement
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return priceID; // Return the retrieved PriceID or -1 if not found
    }

    public static void main(String[] args) {
        PurchaseDAO p = new PurchaseDAO();
         int priceID = 24; // Replace with a valid PriceID
//            String preferredPhone = "123-456-7890";
//            String email = "test@example.com";
//            String fullName = "zzzzzzzzzzzzzzzzzzzzzzzz";
//            String address = "123 Main St, Anytown, USA";
//            List<String> phones = Arrays.asList("111-222-3333", "444-555-6666","334-555-6666");
//            
//            p.addPurchaseGuest(priceID, preferredPhone, email, fullName, address, phones);
p.addPurchaseUser(3, priceID);
            
    }

}
