<%-- 
    Document   : userList
    Created on : Sep 15, 2024, 1:21:24 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="Model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <!-- search xog se ve servlet searchUser r quay lai trang nay -->
        <form action="searchUser" method="GET">
    <input type="text" name="query" placeholder="Search">
    <button type="submit">Search</button>
    
    <!-- list user se o duoi, co the thay doi sau khi search-->
    <%
            Map<Integer,User> list = (Map<Integer, User>)request.getAttribute("list");
            if (list==null || list.size()==0){
                out.println("Empty list of Users");
            }
            else {}
            %>
    
    
</form>
    </body>
</html>
