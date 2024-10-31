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

    private Object ex;
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
// List all tất cả người dùng

    public List<User> getAllUser() {

        List<User> list = new ArrayList<>();
        //Câu truy vấn trên sẽ lấy toàn bộ thông tin người dùng cùng với tên vai trò của họ từ bảng `
        
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
        
        System.out.println(cDAO.getUserByEmail("bob.smith@example.com"));

    }

  

    public List<User> filterUser(String gender, String role, String status, int offset, int limit) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT u.*, r.roleName FROM [dbo].[User] AS u LEFT JOIN Role AS r ON u.roleID = r.roleID WHERE Gender LIKE ? AND roleName LIKE ? AND Status LIKE ? ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            st.setInt(4, offset);
            st.setInt(5, limit);
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

    public List<User> getUserSearchFilter(String gender, String role, String status, int offset, int limit, String str) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT u.*, r.roleName \n"
                    + "FROM [dbo].[User] AS u \n"
                    + "LEFT JOIN Role AS r ON u.roleID = r.roleID \n"
                    + "WHERE \n"
                    + "    (Gender LIKE ? AND roleName LIKE ? AND Status LIKE ?) \n"
                    + "    AND (FullName LIKE ? OR Phone LIKE ? OR Email LIKE ?)\n"
                    + "\n"
                    + "	ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            PreparedStatement st = connection.prepareStatement(sql);
            String searchTerm = "%" + str + "%";
            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            st.setString(4, searchTerm);
            st.setString(5, searchTerm);
            st.setString(6, searchTerm);
            st.setInt(7, offset);
            st.setInt(8, limit);
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
        String sql = "Select u.[UserID],u.[Status], u.[FullName], u.[DateOfBirth], u.[Email], u.[Password], u.[Phone], u.[Address],u.[Gender], r.[RoleName], r.[RoleID], u.[Avatar]\n"
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
                u.setStatus(rs.getString("Status"));
                Role role = new Role();
                role.setRoleName(rs.getString("RoleName"));
                role.setRoleId(rs.getInt("RoleID"));
                u.setRole(role);
                u.setAvatar(rs.getString("Avatar"));

            }
        } catch (Exception e) {
        }
        return u;

    }
//Lấy thông tin của người dùng thông qua email

    public User getUserByEmail(String email) {
        User u = null;

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM [User] WHERE Email= ? AND Status = 'Active'";

            st = connection.prepareStatement(sql);
            st.setString(1, email);

            rs = st.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("UserID");
                String fullName = rs.getString("FullName");
                String dateOfBirth = rs.getString("DateOfBirth");
                String password = rs.getString("Password");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String gender = rs.getString("Gender");
                
                
                String avatar = rs.getString("Avatar");
                int roleId = rs.getInt("RoleID");

                Role role = new RoleDAO().selecById(roleId);

                u = new User(userId, fullName, dateOfBirth, email, password, phone, address, avatar, role, avatar);
            }

        } catch (Exception e) {
            System.out.println(e);

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

    public boolean checkMailRegister(String email) {
        boolean check = false;
        try {
            final String sql = "SELECT * FROM [User] WHERE Email=?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }
//Dùng để thay đổi paswrod của người dùng

    public int updatePasswordByEmail(String email, String password) {
        int check = 0;

        try {
            final String sql = "UPDATE [User]\n"
                    + "SET  Password=? \n"
                    + "WHERE Email=?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            check = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) FROM [dbo].[User]";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 in case of any error
    }

    

    public int getTotalUserFilterCount(String gender, String role, String status) {
        String sql = "SELECT COUNT(*)  FROM [dbo].[User] AS u LEFT JOIN Role AS r ON u.roleID = r.roleID WHERE Gender LIKE ? AND roleName LIKE ? AND Status LIKE ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 in case of any error
    }

    public int getTotalUserSearchFilterCount(String gender, String role, String status, String str) {
        String sql = "SELECT COUNT(*)  \n"
                + "FROM [dbo].[User] AS u \n"
                + "LEFT JOIN Role AS r ON u.roleID = r.roleID \n"
                + "WHERE \n"
                + "    (Gender LIKE ? AND roleName LIKE ? AND Status LIKE ?) \n"
                + "    AND (FullName LIKE ? OR Phone LIKE ? OR Email LIKE ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            String searchTerm = "%" + str + "%";
            st.setString(1, "%" + gender + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + status + "%");
            st.setString(4, searchTerm);
            st.setString(5, searchTerm);
            st.setString(6, searchTerm);
            ResultSet rs = st.executeQuery();
            
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

    public void insertNewUserStudent(User user) {
        PreparedStatement st = null;

        try {
            // SQL query for inserting a new user
            String sql = "INSERT INTO [User] (FullName, Email, Password, Phone, Address, Gender, DateOfBirth, Avatar, RoleID, Status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1, 'Active')";

            // Preparing the statement
            st = connection.prepareStatement(sql);

            // Setting parameters for the query
            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getPhone());
            st.setString(5, user.getAddress());
            st.setString(6, user.getGender());
            st.setString(7, user.getDateOfBirth());
            st.setString(8, user.getAvatar());

            // Executing the update
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
//Check xem email có tồn tại hay chưa

    public boolean checkEmailDAO(String email) {
        boolean exists = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM [User] WHERE Email = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, email);  // Set the email parameter

            rs = st.executeQuery();  // Execute query

            // If a result is returned, email exists
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exists;
    }

    //lấy thông tin người dùng qua id
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT u.[UserID], u.[FullName], u.[DateOfBirth], u.[Email], u.[Password], u.[Phone], u.[Address], u.[Gender], r.[RoleName], u.[Avatar] "
                + "FROM [dbo].[User] u "
                + "JOIN [dbo].[Role] r ON u.RoleID = r.RoleID "
                + "WHERE u.[UserID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setDateOfBirth(rs.getString("DateOfBirth"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setGender(rs.getString("Gender"));

                Role role = new Role();
                role.setRoleName(rs.getString("RoleName"));
                user.setRole(role);
                user.setAvatar(rs.getString("Avatar"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user by ID: " + e.getMessage());
        }

        return user;
    }

    public void updateUserRoleStatus(int id, int role, String Status) {
        String sql = "UPDATE [dbo].[User] set roleID=?, Status=? WHERE userID=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            st.setString(2, Status);
            st.setInt(3, id);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
