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
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
                if (login) {
        %>

        <header>
            <%@include file="header_LoginUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back </h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section><!-- load add orders of user at here -->
            <%
                ArrayList<Order> list = (ArrayList<Order>) request.getAttribute("list");
                String[] status = {"", "processing", "completed", "canceled"};
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
            %>
            <table class="order">
                <tr>
                    <td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's Status</td><td>Action</td>   
                </tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%><br>
                        <% if (ord.getStatus() == 1){ %><a href="mainController?action=cancelOrder&orderID=<%= ord.getOrderID() %>&status=<%= ord.getStatus() %>">Cancel order</a>
                         <% }if (ord.getStatus() == 2) %><a href="#">Completed order</a>
                         <% if (ord.getStatus() == 3){%><a href="mainController?action=cancelOrder&orderID=<%= ord.getOrderID() %>&status=<%= ord.getStatus() %>">Order again</a>
                         <% }%>
                    </td>
                    <td><a href="OrderDetail.jsp?orderid=<%= ord.getOrderID() %>"> detail </a></td></tr>
            </table>
            <% 
}
}
}
else   {      

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
