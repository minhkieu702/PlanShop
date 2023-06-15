<%-- 
    Document   : ManageOrders
    Created on : Mar 22, 2023, 12:50:47 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.email==null}">
                <p>Access denied.</p>
            </c:when>
            <c:otherwise>
                <c:if test="${requestScope.orders!=null}">
                    <table>
                        <tr><th>ID</th><th>Order date</th><th>Ship date</th><th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.orders}" var="o">
                            <tr>
                                <td>${o.orderID}</td><td>${o.orderDate}</td><td>${o.shipDate}</td><td>
                                    <c:if test="${o.status == 1}">processing</c:if>
                                    <c:if test="${o.status == 2}">completed</c:if>
                                    <c:if test="${o.status == 3}">canceled</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </c:otherwise>
        </c:choose>
    </body>
</html>
