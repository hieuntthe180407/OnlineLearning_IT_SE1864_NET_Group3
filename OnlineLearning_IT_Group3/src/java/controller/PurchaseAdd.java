/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.PurchaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.User;

/**
 *
 * @author trong
 */
@WebServlet(name="PurchaseAdd", urlPatterns={"/PurchaseAdd"})
public class PurchaseAdd extends HttpServlet {
   
    

   
  @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("acc");
    
    PrintWriter out = response.getWriter(); // Create PrintWriter for output
    int cID = Integer.parseInt(request.getParameter("CourseID"));
    
    PurchaseDAO pDAO = new PurchaseDAO();
    int priceID = pDAO.getPriceIDbyCourseID(cID);
    
    out.println("Course ID: " + cID); // Debug output
    out.println("Price ID: " + priceID); // Debug output

    if (user == null) {
        List<String> phoneNumbers = new ArrayList<>(Arrays.asList(request.getParameterValues("Phone")));
        
        // Print the list of phone numbers for debugging
        out.println("Phone Numbers: ");
        for (String phoneNumber : phoneNumbers) {
            out.println(phoneNumber); // Print each phone number
        }
        
        Integer phone1 = null;
        Integer phone2 = null;
        Integer phone3 = null;

        // Check if the list is not null and has values
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            try {
                if (!phoneNumbers.get(0).isEmpty()) {
                    phone1 = Integer.parseInt(phoneNumbers.get(0));
                }
                if (phoneNumbers.size() > 1 && !phoneNumbers.get(1).isEmpty()) {
                    phone2 = Integer.parseInt(phoneNumbers.get(1));
                }
                if (phoneNumbers.size() > 2 && !phoneNumbers.get(2).isEmpty()) {
                    phone3 = Integer.parseInt(phoneNumbers.get(2));
                }
            } catch (NumberFormatException e) {
                out.println("Error parsing phone numbers: " + e.getMessage());
            }
        }
        
        // Log retrieved phone numbers for debugging
        out.println("Phone 1: " + phone1);
        out.println("Phone 2: " + phone2);
        out.println("Phone 3: " + phone3);

        String name = request.getParameter("FullName");
        String preferPhone = request.getParameter("PreferredPhone");
        out.println(preferPhone);
        String address = request.getParameter("Address");
        String email = request.getParameter("Email");

        pDAO.addPurchaseGuest(priceID, preferPhone, email, name, address, phoneNumbers);
    } else {
        pDAO.addPurchaseUser(user.getUserID(), priceID);
    }
}



    
}
