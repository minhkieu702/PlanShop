<%-- 
    Document   : viewPlant
    Created on : Mar 20, 2023, 3:50:20 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <table>
            <tr><td rowspan="8"><img src="${plantObj.imgpath}" /></td></tr>
        <tr><td>id: ${plantObj.id}</td></tr>
        <tr><td>product name: ${plantObj.name}</td></tr>
        <tr><td>price: ${plantObj.price}</td></tr>
        <tr><td>description: ${plantObj.description}</td></tr>
        <tr><td>status: ${plantObj.status}</td></tr>
        <tr><td>cate id: ${plantObj.cateid}</td></tr>
        <tr><td>category: ${plantObj.catename}</td></tr>
            
        </table>
    </body>
</html>
