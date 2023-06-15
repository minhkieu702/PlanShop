<%-- 
    Document   : header_LoginAdmin
    Created on : Mar 20, 2023, 4:08:33 PM
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
            <ul>
                <li><a href="mainController?action=manageAccount">Manage Account</a></li>
                <li><a href="mainController?action=manageOrder">Manage Order</a></li>
                <li><a href="#">Manage Plants</a></li>
                <li><a href="#">Manage Categories</a></li>
                <li>Welcome ${sessionScope.name} | <a href="#">Log out</a></li>
                
            </ul>
         
        </header>
    </body>
</html>
