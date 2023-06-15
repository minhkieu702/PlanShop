<%-- 
    Document   : ManageAccount
    Created on : Mar 20, 2023, 4:24:32 PM
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
        <c:import url="header_LoginAdmin.jsp"></c:import>
        <form action="mainController">
            <input type="text" name="txtSearch">
            <input type="submit" value="searchAccount" name="action">
        </form>
        <h1></h1>
        <table class="order">
            <tr>
                <th> ID </th>
                <th> Email </th>
                <th> Full name </th>
                <th> status </th>
                <th> phone </th>
                <th> role </th>
                <th> action </th>
            </tr>
        <c:forEach items="${requestScope.accounts}" var="acc">
            <tr><td><c:out value="${acc.getAccID()}"></c:out></td>
            <td><c:out value="${acc.getEmail()}"></c:out></td>
            <td><c:out value="${acc.getFullname()}"></c:out></td>
            <td><c:choose>
                    <c:when test="${acc.getStatus() eq 1}"> active </c:when>
                    <c:otherwise> inActive</c:otherwise>
            </c:choose></td>
            <td><c:out value="${acc.getPhone()}"></c:out></td>
            <td><c:choose>
                    <c:when test="${acc.getRole() eq 1}"> admin </c:when>
                    <c:otherwise> user </c:otherwise>
            </c:choose></td>
            <td>
            <c:if test="${acc.getRole() eq 0}">
                <c:url value="mainController" var="mylink">
                    <c:param value="${acc.getEmail()}" name="email"></c:param>
                    <c:param value="${acc.getStatus()}" name="status"></c:param>
                    <c:param value="updateStatusAccount" name="action"></c:param>                   
                </c:url>
                <a href="${mylink}">Block/UnBlock</a>
            </c:if>
            </td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
