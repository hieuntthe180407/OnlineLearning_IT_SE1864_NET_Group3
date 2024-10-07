/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ImportServlet", urlPatterns = {"/ImportServlet"})
@MultipartConfig
public class ImportServlet extends HttpServlet {

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
        request.getRequestDispatcher("questionImport.jsp").forward(request, response);

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
        String course = request.getParameter("course");

        Part filePart = request.getPart("fileInput"); // lấy file từ form
        InputStream fileContent = filePart.getInputStream();

        Workbook workbook = new XSSFWorkbook(fileContent);
        Sheet sheet = workbook.getSheetAt(0);

        boolean hasErrors = false;

        //Check xem có course trong database
        CourseDAO courseDAO = new CourseDAO();
        if (!courseDAO.checkCourseByName(course)) {
            request.setAttribute("errorImport", "Wrong course to add.");
            request.getRequestDispatcher("questionImport.jsp").forward(request, response);
            return;
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Bắt đầu từ hàng 1, bỏ qua tiêu đề
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            String questionContent = getCellValue(row, 0);
            String questionType = getCellValue(row, 1);
            String questionPath = getCellValue(row, 2);
            String level = getCellValue(row, 3);
            String correctAnswer = getCellValue(row, 4);

            String error = "";

            // Kiểm tra các trường bắt buộc
            if (questionContent == null || questionContent.isEmpty()) {
                error += "Thiếu nội dung câu hỏi. ";
            }
            if (questionType == null || questionType.isEmpty()) {
                error += "Thiếu loại câu hỏi. ";
            }
            if (level == null || (!level.equals("Easy") && !level.equals("Medium") && !level.equals("Hard"))) {
                error += "'Level' chỉ nhận 'Easy', 'Medium', 'Hard'. ";
            }
            if (correctAnswer == null || correctAnswer.isEmpty()) {
                error += "Thiếu câu trả lời đúng.";
            }

            if (!error.isEmpty()) {
                //Từ đây xử lý in ra cột bên cạnh khi có lỗi
                hasErrors = true;
                Cell errorCell = row.createCell(row.getLastCellNum());
                errorCell.setCellValue(error);
            } else {
                //Từ đây cho question vào database

            }
        }

        if (hasErrors) {
            // Trả lại file với lỗi
            response.setHeader("Content-Disposition", "attachment; filename=Errors_in_Import.xlsx");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
                workbook.close();
            }
        } else {
            // Xử lý nếu không có lỗi
            response.getWriter().println("File không có lỗi.");
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        return (cell == null) ? null : cell.toString();
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
