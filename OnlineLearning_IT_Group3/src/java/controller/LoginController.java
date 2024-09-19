/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.PasswordEncryption;

/**
 *
 * @author DTC
 */
@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Cookie [] cookies = request.getCookies();
        
        String email ="";
        String pass="";
        String check=null;
        
        
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if ("email".equals(cookie.getName()))
                {
                    email=cookie.getValue();
                }
                
                 if ("pass".equals(cookie.getName()))
                {
                    email=cookie.getValue();
                }
                 
                  if ("checked".equals(cookie.getName()))
                {
                    email=cookie.getValue();
                }
                 
            }
        }
        
        request.setAttribute("email", email);
        request.setAttribute("pass", pass);
        request.setAttribute("checked", check);
        
        
        HttpSession session = request.getSession();
        
        String msg1 = (String) session.getAttribute("msg1");
        request.setAttribute("msg1", msg1);
        session.removeAttribute("msg1");
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ;
        
        String url = request.getScheme() +"://" +request.getServerName() + ":" +request.getServerPort() + request.getContextPath();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600);
        
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        String checked[] = request.getParameterValues("check");
        
        String c = "checked";
        if(checked == null){
         c = "notchecked";
        }
        
        UserDAO ud = new UserDAO();
        User u = ud.getUserByEmail(email);
        
        Cookie cuser = new Cookie("email", email);
        Cookie puser = new Cookie("pass", password);
        Cookie ruser = new Cookie("checked", c);
        ruser.setMaxAge(60 * 60 * 24 * 7);//
        response.addCookie(ruser);
        if (u == null) {
            url += "/login";
            String failed = "Account is not registered!!! ";
            request.setAttribute("failed", failed);
            request.setAttribute("email", email);
            cuser.setMaxAge(0);
            puser.setMaxAge(0);
         
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (!PasswordEncryption.verify(password, u.getPassword())) {
                url += "/login";
                String failed = "Password incorrect!";
                request.setAttribute("failed", failed);
                request.setAttribute("email", email);

                cuser.setMaxAge(0);
                puser.setMaxAge(0);
                request.setAttribute("failed", failed);

                
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("acc", u);
                if (!c.equals("notchecked")) {
                    cuser.setMaxAge(60 * 60 * 24 * 7);
                    puser.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cuser);
                    response.addCookie(puser);
                } else {
                    cuser.setMaxAge(0);
                    puser.setMaxAge(0);
                }
                if (u.getRole().getRoleId() == 1) {
                    session.setAttribute("acc", u);
                    url += "/home.jsp";
                } else if (u.getRole().getRoleId() == 2) {
                    session.setAttribute("teacher", u);
                    url += "/home.jsp";
                } else if (u.getRole().getRoleId() == 3) {
                    session.setAttribute("admin", u);
                    url += "/Admin.jsp";
                } else {
                    try {
                        throw new Exception();
                    } catch (Exception ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                response.sendRedirect(url);
            }

        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
