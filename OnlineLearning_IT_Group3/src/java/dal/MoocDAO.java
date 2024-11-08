/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Mooc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Mooc;


/**
 *
 * @author trong
 */
public class MoocDAO extends DBContext {

      public List<Mooc> getAllMoocByCourseID(int courseID) {
        List<Mooc> list = new ArrayList<>();
        try {
            String sql = "Select * from Mooc WHERE CourseID=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Mooc m = new Mooc();
                m.setMoocId(rs.getInt("moocID"));
                m.setMoocName(rs.getString("MoocName"));
                m.setMoocNumber(rs.getInt("MoocNumber"));
                
                list.add(m);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
      public static void main(String[] args) {
        MoocDAO m  = new MoocDAO();
        List<Mooc> mooc = m.getAllMoocByCourseID(3);
          for (Mooc mooc1 : mooc) {
              System.out.println(mooc1.getMoocName()+"\n");
          }
    }


    public int getFisrtMoocID(int courseid) {
        try {
            String sql = "select MoocID from Mooc where CourseID = " + courseid + " order by MoocNumber asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public ArrayList getMoocByCourseId(int CourseId) {
        ArrayList<Mooc> listMooc = new ArrayList<Mooc>();

        try {
            String sql = "SELECT MoocID, MoocNumber, MoocName FROM Mooc WHERE CourseID=? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CourseId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int moocId = rs.getInt("MoocID");
                int moocNumber = rs.getInt("MoocNumber");
                String moocName = rs.getString("MoocName");
                Mooc mooc = new Mooc(moocId, moocNumber, moocName);
                listMooc.add(mooc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listMooc;
    }

    public Mooc selectById(int id) {

        Mooc mooc = null;
        try {
            String sql = "SELECT * from Mooc WHERE MoocID=? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int moocId = rs.getInt("MoocID");
                int moocNumber = rs.getInt("MoocNumber");
                String moocName = rs.getString("MoocName");
                Course course = new CourseDAO().getCourseByID(rs.getInt("CourseID"));
                mooc = new Mooc(moocId, moocNumber, moocName, course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mooc;

    }

    public List<Mooc> selectByCourseID(int id) {
        List<Mooc> moocs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Mooc WHERE CourseID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int moocId = rs.getInt("MoocID");
                int moocNumber = rs.getInt("MoocNumber");
                String moocName = rs.getString("MoocName");
                Course course = new CourseDAO().getCourseByID(rs.getInt("CourseID"));
                Mooc mooc = new Mooc(moocId, moocNumber, moocName, course);
                moocs.add(mooc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return moocs;
    }

    public boolean addANewMooc(String number, String MoocName, String courseid) {
        try {
            String sql = "insert into Mooc ([MoocNumber], [MoocName], [CourseID]) values\n"
                    + "  (" + number + " , '" + MoocName + "' , " + courseid + ")";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int GetIdMooc(int number, int courseid) {
        try {
            String sql = "  select * from [Mooc] where [MoocNumber] = " + number + " and [CourseID] = " + courseid + ";";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MoocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public boolean deleteMooc(String moocid, String courseid) {
        String sql = "DELETE FROM Lessons WHERE MoocID = ? DELETE FROM Mooc WHERE MoocID = ? AND CourseID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(moocid));
            preparedStatement.setInt(2, Integer.parseInt(moocid));
            preparedStatement.setInt(3, Integer.parseInt(courseid));

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMooc(String moocid, String moocname) {
        String sql = "UPDATE Mooc SET MoocName = ? WHERE MoocID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, moocname);
            preparedStatement.setInt(2, Integer.parseInt(moocid));

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}

