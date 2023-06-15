<%-- 
    Document   : ChangedProfile
    Created on : Feb 28, 2023, 4:54:16 PM
    Author     : Acer
--%>

<%@page import="data.Account"%>
<%@page import="myDAO.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <header>
       <%@include file="header_LoginUser.jsp" %>
    </header>
    <body>
        <h1>Changed Profile</h1>
        <% 
         String email = (String) session.getAttribute("email");
         for (Account acc : AccountDAO.getAccounts()) {
                 if(acc.getEmail().equals(email)){
                 %>
                 <form action="mainController" method="post">
                     New name : <input type="text" name="newName" value="<%= acc.getFullname() %>"></br>
                     New phone: <input type="text" name="newPhone" value="<%= acc.getPhone()%>"></br>
            <input type="submit" value="Change" name="action">
        </form>
                 <%
                     
            }
             }
        
        %>
        
    </body>
    <footer>
                <%@include file="footer.jsp" %>
            </footer>
</html>
