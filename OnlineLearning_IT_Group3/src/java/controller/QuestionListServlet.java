/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Question;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuestionListServlet", urlPatterns = {"/QuestionListServlet"})
public class QuestionListServlet extends HttpServlet {

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
        QuestionDAO qDao = new QuestionDAO();

        // Kiểm tra tham số "page"
        String pageParam = request.getParameter("page");
        int page = 1; // Giá trị mặc định
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Kiểm tra tham số "questionPerPage"
        String questionPerPageParam = request.getParameter("questionPerPage");
        int questionPerPage = 10; // Giá trị mặc định
        if (questionPerPageParam != null && !questionPerPageParam.isEmpty()) {
            try {
                questionPerPage = Integer.parseInt(questionPerPageParam);
            } catch (NumberFormatException e) {
                questionPerPage = 10;
            }
        }
        //Lấy tổng số trang
        int totalPages = qDao.getTotalPages(
                request.getParameter("questionTitle"),
                request.getParameter("questionCourse"),
                request.getParameter("questionType"),
                request.getParameter("questionLevel"),
                request.getParameter("questionStatus"),
                questionPerPage
        );
        // Lấy danh sách câu hỏi với các tham số đã xác định
        List<Question> listQuestion = qDao.getFilteredQuestions(
                request.getParameter("questionTitle"),
                request.getParameter("questionCourse"),
                request.getParameter("questionType"),
                request.getParameter("questionLevel"),
                request.getParameter("questionStatus"),
                page,
                questionPerPage
        );
        request.setAttribute("listQuestion", listQuestion);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("questionList.jsp").forward(request, response);
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
        String questionTitle = request.getParameter("questionTitle");
        String questionCourse = request.getParameter("questionCourse");
        String questionType = request.getParameter("questionType");
        String questionLevel = request.getParameter("questionLevel");
        String questionStatus = request.getParameter("questionStatus");

        // Kiểm tra tham số "page"
        String pageParam = request.getParameter("page");
        int page = 1; // Giá trị mặc định
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Kiểm tra tham số "questionPerPage"
        String questionPerPageParam = request.getParameter("questionPerPage");
        int questionPerPage = 10; // Giá trị mặc định
        if (questionPerPageParam != null && !questionPerPageParam.trim().isEmpty()) {
            try {
                questionPerPage = Integer.parseInt(questionPerPageParam);
            } catch (NumberFormatException e) {
                questionPerPage = 10;
            }
        }

        QuestionDAO qDao = new QuestionDAO();
        //List question theo filter
        List<Question> listQuestion = qDao.getFilteredQuestions(questionTitle, questionCourse, questionType, questionLevel, questionStatus, page, questionPerPage);
        //Lấy tổng số trang
        int totalPages = qDao.getTotalPages(questionTitle, questionCourse, questionType, questionLevel, questionStatus, questionPerPage);

        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listQuestion", listQuestion);
        request.getRequestDispatcher("questionList.jsp").forward(request, response);

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
