/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import model.User;

/**
 *
 * @author trong
 */
public class UserDAO extends DBContext {
//    public Map<Integer, User> getAllUser(){
//        Map<Integer, User> list = new HashMap<>();
//        try {
//            String sql = "Select * from [dbo].[User]";
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()){
//                User u = new User();
//                u.setUserID(rs.getInt("userID"));
//                 u.setFullName(rs.getString("fullName"));
//                u.setEmail(rs.getString("Email"));
//                u.setPassword(rs.getString("Password"));
//                u.setAddress(rs.getString("Address"));
//                u.setRoleID(rs.getInt("RoleID"));
//                list.put(u.getUserID(), u);
//            }
//            rs.close();
//            st.close();
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return list;
//    }

    public List<User> getAllUser() {

        List<User> list = new ArrayList<>();

        String sql = "Select u.[UserID], u.[FullName], u.[DateOfBirth], u.[Email], u.[Password], u.[Phone], u.[Address],u.[Gender], r.[RoleName]\n"
                + "from [dbo].[User] u, [dbo].[Role] r\n"
                + "where r.RoleID = u.RoleID ;";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setGender(rs.getString("Gender"));
                u.setPhone(rs.getString("Phone"));
                u.setAddress(rs.getString("Address"));

                Role role = new Role();
                role.setRoleName(rs.getString("RoleName"));
                u.setRole(role);

                list.add(u);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        UserDAO cDAO = new UserDAO();
        List<User> list = cDAO.getAllUser();
        for (User u : list) {
            System.out.println(u);
        }
    }

    public List<User> searchUser(String str) {
        List<User> list = new ArrayList<>();
        try {

            String sql = "SELECT * FROM [dbo].[User] WHERE FullName LIKE ? OR Phone LIKE ? OR Email LIKE ?";
            PreparedStatement st = connection.prepareStatement(sql);

            String searchTerm = "%" + str + "%";

            st.setString(1, searchTerm);
            st.setString(2, searchTerm);
            st.setString(3, searchTerm);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setGender(rs.getString("Gender"));
                u.setPassword(rs.getString("Password"));
                u.setAddress(rs.getString("Address"));
                u.setPhone(rs.getString("Phone"));
                Role role = new Role();
                role.setRoleId(rs.getInt("RoleID"));
                u.setRole(role);
                list.add(u);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<User> filterUser(String gender, String role, String status) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT u.*, r.roleName FROM [dbo].[User] AS u LEFT JOIN Role AS r ON u.roleID = r.roleID WHERE Gender LIKE ? AND roleName LIKE ? AND isVerify LIKE ?";

            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setAddress(rs.getString("Address"));
                u.setGender(rs.getString("Gender"));
                u.setPhone(rs.getString("Phone"));
                Role r = new Role();
                r.setRoleId(rs.getInt("RoleId"));
                r.setRoleName(rs.getString("RoleName"));
                u.setRole(r);
                list.add(u);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public User getUserProfilebyId(int id) {
        String sql = "Select u.[UserID], u.[FullName], u.[DateOfBirth], u.[Email], u.[Password], u.[Phone], u.[Address],u.[Gender], r.[RoleName], u.[Avatar]\n"
                + "from [dbo].[User] u, [dbo].[Role] r\n"
                + "where r.RoleID = u.RoleID AND u.[UserID] = ?;";
        User u = new User();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setDateOfBirth(rs.getString("DateOfBirth"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setGender(rs.getString("Gender"));
                u.setPhone(rs.getString("Phone"));
                u.setAddress(rs.getString("Address"));
                Role role = new Role();
                role.setRoleName(rs.getString("RoleName"));
                u.setRole(role);
                u.setAvatar(rs.getString("Avatar"));

            }
        } catch (Exception e) {
        }
        return u;

    }


    public User getUserByEmail(String email) {
        User u = null;

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            String sql ="SELECT * FROM [User] WHERE Email= ? AND isVerify = 0";

            st = connection.prepareStatement(sql);
            st.setString(1, email);

            rs = st.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("UserID");
                String fullName = rs.getString("FullName");
                Date dateOfBirth = rs.getDate("DateOfBirth");
                String password = rs.getString("Password");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                Boolean gender = rs.getBoolean("Gender");
                String reason = rs.getString("Reason");
                Date timeBan = rs.getDate("TimeBan");
                String avatar = rs.getString("Avatar");
                int roleId = rs.getInt("RoleID");

                Role role = new RoleDAO().selecById(roleId);

                u = new User(userId, fullName, avatar, email, password, phone, address, avatar, role, avatar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return u;
    }

    public void updateUserProfile(User user) {
        String sql = "UPDATE [dbo].[User] "
                + "SET [FullName] = ?, [DateOfBirth] = ?, [Phone] = ?, [Address] = ?, [Gender] = ?, [Avatar] = ? "
                + "WHERE [UserID] = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getFullName());
            st.setString(2, user.getDateOfBirth());
            st.setString(3, user.getPhone());
            st.setString(4, user.getAddress());
            st.setString(5, user.getGender());
            st.setString(6, user.getAvatar());
            st.setInt(7, user.getUserID());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePasswordById(int userId, String newPassword) {
        String sql = "UPDATE [dbo].[User] SET [Password] = ? WHERE [UserID] = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);  // Set the new password
            st.setInt(2, userId);          // Set the user ID

            st.executeUpdate();  // Execute the update

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    
    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) FROM [dbo].[User]";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 in case of any error
    }
    
     public List<User> getUsers(int offset, int limit) {
        List<User> list = new ArrayList<>();
        
        try {
            String query = "SELECT * from [dbo].[User] u, Role r WHERE r.RoleID = u.RoleID  ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, offset);
            st.setInt(2, limit);
            ResultSet rs = st.executeQuery();
                while (rs.next()) {
                      User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setGender(rs.getString("Gender"));
                u.setPhone(rs.getString("Phone"));
                u.setAddress(rs.getString("Address"));

                Role role = new Role();
                role.setRoleName(rs.getString("RoleName"));
                u.setRole(role);

                list.add(u);
                }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
