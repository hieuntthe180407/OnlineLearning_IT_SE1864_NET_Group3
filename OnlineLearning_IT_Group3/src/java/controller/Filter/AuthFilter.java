
package controller.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
    "/addCourse",
    "/blogs",
    "/BlogManageController",
    "/courseApprovalController",
    "/CourseInformationDetailController",

//    "updateCourse",

    //"updateCourse",

//    "/courseList",
     "/deleteQuestionServlet", "/ImportServlet","/QuestionDetailServlet","/questionListShowHide","/ShowPageServlet",
     
    "/forgot",
    "/home",
    "/logout",
//    "/PurchaseAdd",
    "/register",
    "/resetPassword",
//    "/ReviewAdd",
    "/SliderController",
    "/UploadFileController",
    "/UploadImageController",
//    "/courseDetail",
    "/courseEdit",
    "/courseEditSubmit",
    "/editStatusLesson",
    "/filterUser",
    "/lessonEdit",
    "/userAdminEdit",
    "/userList",
    "/userDetail",
    "/verifyServlet",
    "/changePassword",
    "/editProfile",
    "/userProfile",
    "/managerCourse",
    "/addAnswerServlet",
    "/ExportServlet",
    "/QuestionListServlet",
    "/removeOptionServlet",
        "/courseDetailTeacher.jsp"
})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // Danh sách các URL cho phép khách truy cập
        String[] guestPages = {"/home", "/forgot", "/register", "/resetPassword","/verifyServlet"};

        String requestURI = httpRequest.getRequestURI();

        // Kiểm tra nếu URL là trang cho phép khách truy cập
        if (isAuthorizedPage(requestURI, guestPages)) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("acc") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        Integer roleId = ((User) session.getAttribute("acc")).getRole().getRoleId();

        // Danh sách các URL chỉ admin có thể truy cập
        String[] adminPages = {"/addCourse", "/BlogManageController", "/courseApprovalController",
            "/userAdminEdit", "/userList", "/userDetail", "/managerCourse", "/logout"};

        // Danh sách các URL chỉ teacher có thể truy cập
        String[] teacherPages = {"/blogs", "/ReviewAdd", "/UploadFileController", "/UploadImageController","updateCourse","/editProfile","/userProfile","/changePassword","/addAnswerServlet","/deleteQuestionServlet","/ExportServlet", "/ImportServlet","/QuestionDetailServlet", "/QuestionListServlet","/questionListShowHide", "/removeOptionServlet", "/ShowPageServlet",
            "/courseEdit", "/courseEditSubmit", "/editStatusLesson", "/lessonEdit", "/managerCourse", "/logout", "/courseDetail", "/courseList","/courseDetailTeacher.jsp"};

        // Danh sách các URL chỉ student có thể truy cập
        String[] studentPages = {"/courseList", "/courseDetail", "/userProfile", "/changePassword","/editProfile", 
            "/verifyServlet", "/ReviewAdd", "/courseList", "/courseDetail", "/logout"};

        if (roleId == 3) {
            // Admin có thể truy cập tất cả
            chain.doFilter(request, response);
        } else if (roleId == 2) {
            // Teacher
            if (isAuthorizedPage(requestURI, teacherPages)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            }
        } else if (roleId == 1) {
            // Student
            if (isAuthorizedPage(requestURI, studentPages)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            }
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorizedPage(String requestURI, String[] authorizedPages) {
        for (String page : authorizedPages) {
            if (requestURI.contains(page)) {
                return true;
            }
        }
        return false;
    }
}
