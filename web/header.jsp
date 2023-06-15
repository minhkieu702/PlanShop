<%-- 
    Document   : header
    Created on : Feb 15, 2023, 4:16:05 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="mainController?action=viewcart">View Cart</a></li>
                    <li><form action="mainController" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch"))==null?"": request.getParameter("txtsearch") %>">
                    <select name="searchby">
                        <option>by name</option>
                        <option>by category</option>
                    </select>
                    <input type="submit" value="search" name="action">
                </ul>
            </nav> 
        </header>
    </body>
</html>
