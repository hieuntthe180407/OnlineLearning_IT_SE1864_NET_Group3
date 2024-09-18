/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Role;

/**
 *
 * @author DTC
 */
public class RoleDAO extends DBContext{
    public Role selecById(int id){
        Role role = null;
        try {
            String sql ="SELECT * FROM Role WHERE RoleID=?";
            
            PreparedStatement st = connection.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            
            while (rs.next()) {

                int roleId = rs.getInt("RoleID");
                String roleName =rs.getString("RoleName");
                role = new Role(roleId, roleName);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return role;
    }
}
