/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.UserProfile;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Path;
import java.nio.file.Files;

import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
@MultipartConfig
public class editProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        UserDAO u = new UserDAO();
        try {
            //Lấy thông tin người dùng
            User profile = u.getUserProfilebyId(user.getUserID());
            request.setAttribute("profile", profile);
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        UserDAO u = new UserDAO();
        try {
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            String avatar;
            String oldAvatar = request.getParameter("oldAvatar");
            Part imagePart = request.getPart("avatar");
            
            //check điều kiện sô điện thoại
            if (phone.length() != 10) {
                request.setAttribute("errorEditProfile", "Phone number must be exactly 10 digits.");
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
                return;
            }

            //check xem có avatar mới không
            if (imagePart == null || imagePart.getSize() == 0 ) {
                // Giữ lại avatar cũ
                avatar = oldAvatar;
            } else {
                //lấy đường dẫn trong project
                String realPath = request.getServletContext().getRealPath("/imgavatar");
                //lấy tên file
                String fileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
                //nếu folder chưa được tạo thì tạo
                if (!Files.exists(Path.of(realPath))) {

                    Files.createDirectory(Path.of(realPath));

                }
                //xác định vị trí chính xác tệp trong hệ thống
                Path targetPath = Path.of(realPath, fileName);
                // Kiểm tra xem tệp đã tồn tại chưa
                if (Files.exists(targetPath)) {
                    // Nếu tệp đã tồn tại, tạo tên mới cho tệp
                    String newFileName = System.currentTimeMillis() + "_" + fileName; // Thêm timestamp vào tên tệp
                    targetPath = Path.of(realPath, newFileName); // Cập nhật đường dẫn đích
                }
                //Tệp hình ảnh imagePart sẽ được lưu vào vị trí targetPath
                imagePart.write(targetPath.toString());
                // Lưu vào database
                avatar = "imgavatar/" + targetPath.getFileName().toString();

            }

            User pf = u.getUserProfilebyId(user.getUserID());
            pf.setFullName(fullName);
            pf.setGender(gender);
            pf.setDateOfBirth(dateOfBirth);
            pf.setPhone(phone);
            pf.setAddress(address);
            pf.setAvatar(avatar);
            // Lưu thay đổi vào database
            u.updateUserProfile(pf);
            response.sendRedirect("userProfile");
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
