/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AnswerDAO;
import dal.CourseDAO;
import dal.QuestionDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import model.Answer;

import model.Course;
import model.Question;
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
        InputStream fileContent = filePart.getInputStream(); // Lấy InputStream từ đối tượng filePart

        Workbook workbook = new XSSFWorkbook(fileContent); // Tạo một đối tượng Workbook để đại diện cho tệp Excel.
        Sheet sheet = workbook.getSheetAt(0); // Lấy bảng tính (sheet) đầu tiên từ workbook

        boolean hasErrors = false;

        //Check xem có course trong database
        CourseDAO courseDAO = new CourseDAO();
        if (!courseDAO.checkCourseByName(course)) {
            request.setAttribute("errorImport", "Wrong course to add.");
            request.getRequestDispatcher("questionImport.jsp").forward(request, response);
            return;
        }
        int courseID = courseDAO.courseIdByCourseName(course);

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
            String errorContent = "";
            String errorType = "";
            String errorLevel = "";
            String errorAnswer = "";

            // Kiểm tra các trường bắt buộc
            if (questionContent == null || questionContent.isEmpty()) {
                error += "Missing question content. ";
                errorContent += "Missing question content. ";

            }
            if (questionType == null || questionType.isEmpty() || (!questionType.equals("Multiple Choice") && (!questionType.equals("Essay")))) {
                error += "Question type must be Essay or Multipe Choice. ";
                errorType += "Question type must be Essay or Multipe Choice. ";

            }
            if (level == null || (!level.equals("Easy") && !level.equals("Medium") && !level.equals("Hard"))) {
                error += "'Level' must be 'Easy', 'Medium', 'Hard'. ";
                errorLevel += "'Level' must be 'Easy', 'Medium', 'Hard'. ";

            }
            if (correctAnswer == null || correctAnswer.isEmpty()) {
                error += "Missing correct answer.";
                errorAnswer += "Missing correct answer.";

            }

            if (!error.isEmpty()) {
                //Từ đây xử lý in ra cột bên cạnh khi có lỗi
                hasErrors = true;
//                Cell errorCell = row.createCell(row.getLastCellNum());
//                errorCell.setCellValue(error);
                // Update the specific cells with error messages where necessary
                if (!errorContent.isEmpty()) {
                    Cell showErrorContent = row.getCell(0); // Check if the cell already exists
                    if (showErrorContent == null) {
                        showErrorContent = row.createCell(0); // Create the cell if it doesn't exist
                    }
                    showErrorContent.setCellValue(errorContent); // Set the error message
                }

                if (!errorType.isEmpty()) {
                    Cell showErrorType = row.getCell(1); // Check if the cell already exists
                    if (showErrorType == null) {
                        showErrorType = row.createCell(1); // Create the cell if it doesn't exist
                    }
                    showErrorType.setCellValue(errorType); // Set the error message
                }

                if (!errorLevel.isEmpty()) {
                    Cell showErrorLevel = row.getCell(3); // Check if the cell already exists
                    if (showErrorLevel == null) {
                        showErrorLevel = row.createCell(3); // Create the cell if it doesn't exist
                    }
                    showErrorLevel.setCellValue(errorLevel); // Set the error message
                }

                if (!errorAnswer.isEmpty()) {
                    Cell showErrorAnswer = row.getCell(4); // Check if the cell already exists
                    if (showErrorAnswer == null) {
                        showErrorAnswer = row.createCell(4); // Create the cell if it doesn't exist
                    }
                    showErrorAnswer.setCellValue(errorAnswer); // Set the error message
                }

            } else {
                if (questionPath != null && (questionPath.endsWith(".jpg") || questionPath.endsWith(".png") || questionPath.endsWith(".mp4"))) {
                    String realPath = request.getServletContext().getRealPath("/imgQuestion");

                    if (!Files.exists(Path.of(realPath))) {

                        Files.createDirectory(Path.of(realPath));

                    }

                    String fileName = Path.of(questionPath).getFileName().toString();// Lấy tên tệp
                    Path targetPath = Path.of(realPath, fileName); // kết hợp đường dẫn project với tên tệp

                    Path sourcePath = Path.of(questionPath);// đường dẫn gốc của tệp

                    // Kiểm tra xem tệp đã tồn tại chưa
                    if (Files.exists(targetPath)) {
                        // Nếu tệp đã tồn tại, tạo tên mới cho tệp
                        String newFileName = System.currentTimeMillis() + "_" + fileName; // Thêm timestamp
                        targetPath = Path.of(realPath, newFileName); // Cập nhật đường dẫn đích
                    }
                    //Copy đường dẫn gốc sang nơi muốn lưu tệp
                    Files.copy(sourcePath, targetPath);
                    //Ghi đường dẫn vào database
                    questionPath = "imgQuestion" + "/" + fileName;

                }
                //Từ đây cho question vào database
                Question importedQuestion = new Question();
                importedQuestion.setQuestionContent(questionContent);
                importedQuestion.setQuestionTitle(questionContent);
                importedQuestion.setQuestionType(questionType);
                importedQuestion.setQuestionImgOrVideo(questionPath);
                importedQuestion.setLevel(level);
                importedQuestion.setStatus("Visible");
                importedQuestion.setExplanation("Nothing");
                
                Course questionCourse = new Course();
                questionCourse.setCourseID(courseID);
                importedQuestion.setCourse(questionCourse);

                // Lưu câu hỏi vào cơ sở dữ liệu
                QuestionDAO questionDAO = new QuestionDAO();
                int questionId = questionDAO.importQuestion(importedQuestion);
                importedQuestion.setQuestionId(questionId);

                // Tạo và thiết lập các thuộc tính cho câu trả lời
                Answer answerQuestion = new Answer();
                answerQuestion.setOptionContent(correctAnswer);
                answerQuestion.setQuestion(importedQuestion);

                // Lưu câu trả lời vào cơ sở dữ liệu
                AnswerDAO answerDAO = new AnswerDAO();
                answerDAO.importAnswer(answerQuestion, true);

                int rowIndex = row.getRowNum();
                int lastRowNum = sheet.getLastRowNum();
                sheet.removeRow(row);

                if (rowIndex >= 0 && rowIndex < lastRowNum) {
                    sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
                }
                i--;
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
            request.setAttribute("errorImport", "Success Import with no error.");
            request.getRequestDispatcher("questionImport.jsp").forward(request, response);

        }
    }

    private String getCellValue(Row row, int cellIndex) {
        //Lấy giá trị trong 1 cell
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
