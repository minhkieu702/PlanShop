<%-- 
    Document   : order
    Created on : Mar 15, 2023, 4:09:50 PM
    Author     : Acer
--%>

<%-- 
    Document   : personalPage
    Created on : Feb 15, 2023, 4:42:56 PM
    Author     : Acer
--%>

<%@page import="myDAO.AccountDAO"%>
<%@page import="data.Account"%>
<%@page import="myDAO.OrderDAO"%>
<%@page import="data.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p><font color='red'>you must login to view personal page</font></p>
        <p></p>
        <%
        } else {
        %>
        <header>
            <%@include file="header_LoginUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back </h3>
            <h3><a href="mainController?action=logout">logout</a></h3>
        </section>
        <section>
            <!--load all orders of the user at here-->
            <%
                String email = (String) session.getAttribute("email");
                int sta = Integer.parseInt(request.getParameter("gt"));
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                int orderID = 0;
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
                        if (ord.getStatus() == sta) {
            %>
            <table class="order">
                <tr><td>Order ID</td> <td>Order Date</td> <td>Ship Date</td> <td>Order's status</td><td>action</td></tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/> <% if (ord.getStatus() == 1) %><a href="mainController?action=cancelOrder&orderID=<%= ord.getOrderID() %>&status=<%= 1 %>">Cancel order</a>
                        <br/> <% if (ord.getStatus() == 2) %><a href="#">Completed order</a>
                        <br/> <% if (ord.getStatus() == 3) %><a href="mainController?action=cancelOrder&orderID=<%= ord.getOrderID() %>&status=<%= 3 %>">Canceled</a>
                    </td>
                    <td><a href="OrderDetail.jsp?orderid=<%= ord.getOrderID()%>">detail</a></td>
                </tr>
            </table>
            <%
                }
            %>    


            <%
                }
            } 
    else {
            %>
            <p>you don't have any order</p>
            <%
                }          
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }
        %>

    </body>
</html>