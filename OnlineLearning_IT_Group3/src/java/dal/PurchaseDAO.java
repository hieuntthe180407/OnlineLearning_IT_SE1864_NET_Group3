/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;

/**
 *
 * @author trong
 */
public class PurchaseDAO extends DBContext {
    public void addReview(String Address,String phone, String gName,String price)
   {
       try {
            String sql = "Insert into Purchase(CourseID,UserID,ReviewContent) values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
           
                
            st.executeUpdate();
            st.close();
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            
        }
   }
}
