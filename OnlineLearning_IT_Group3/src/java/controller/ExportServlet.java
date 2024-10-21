/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ExportServlet", urlPatterns = {"/ExportServlet"})
public class ExportServlet extends HttpServlet {

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
        request.getRequestDispatcher("questionImport.jsp").forward(request, response);
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
        //Tạo file Excel tên SampleQuestion
        response.setHeader("Content-Disposition", "attachment; filename=SampleQuestion.xlsx");
        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Thiết lập độ rộng cho các cột (đơn vị tính là 1/256 của độ rộng ký tự)
        sheet.setColumnWidth(0, 8000);  // Column for "Question content"
        sheet.setColumnWidth(1, 4000);  // Column for "Question Type"
        sheet.setColumnWidth(2, 8000);  // Column for "Question imagine or video"
        sheet.setColumnWidth(3, 4000);  // Column for "Level"
        sheet.setColumnWidth(4, 6000);  // Column for "Correct Answer"

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Quetion content/Question Title");
        headerRow.createCell(1).setCellValue("Question Type( Essay or Multipe Choice )");
        headerRow.createCell(2).setCellValue("Question imagine or video path:");
        headerRow.createCell(3).setCellValue("Level");
        headerRow.createCell(4).setCellValue("Correct Answer");

        // Thêm dữ liệu Mẫu
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("1+1=?");
        dataRow.createCell(1).setCellValue("Essay");
        dataRow.createCell(2).setCellValue("Insert imagine or video path");
        dataRow.createCell(3).setCellValue("Easy");
        dataRow.createCell(4).setCellValue("2");

        // Ghi dữ liệu ra response
        ServletOutputStream outputStream = response.getOutputStream();
        //Ghi nội dung file Excel vào luồng xuất để truyền cho người dùng.
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
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
