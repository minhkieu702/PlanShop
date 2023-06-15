<%-- 
    Document   : header_LoginUser
    Created on : Feb 15, 2023, 4:42:40 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="ChangedProfile.jsp">Change Profile</a></li>
                <li><a href="Order.jsp?gt=<%= 2 %>">Completed Orders</a></li>
                <li><a href="Order.jsp?gt=<%= 3 %>">Canceled Orders</a> </li>
                <li><a href="Order.jsp?gt=<%= 1 %>">Processing Orders</a> </li>
                        <li><form action="mainController">
                                from<input type="date" name="from"> 
                                to <input type="date" name="to"> 
                                <!--<input type="submit" value="Filter" name="action">-->
                                <button value="Filter" name="action">Search</button>
                    </form></li>
            </ul> 
        </nav>
    </body>
</html>
