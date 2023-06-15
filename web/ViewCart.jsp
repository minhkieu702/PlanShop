<%-- 
    Document   : ViewCart
    Created on : Mar 16, 2023, 3:55:26 AM
    Author     : Acer
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" tyle="text/css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
            String name = (String) session.getAttribute("name");
            if (name!=null) {
                    %>
                    <h3>Welcome <%= session.getAttribute("name") %> come back</h3>
                    <h3><a href="mainController?action=logout">Logout</a></h3>
                    <h3><a href="beforePP">personal page</a></h3>
            <%
                }
            %>
            <font style='color:red;'><%= (request.getAttribute("WARNING")==null)?"":(String)request.getAttribute("WARNING")%></font>

            <table width="100%" class="shopping">
                <tr><td>Product ID</td><td>Quantity</td><td>action</td></tr>
                <%
                    HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    if (cart != null) {
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            int quantity = cart.get(pid);
                %>
                <form action="mainController" method="post">
                    <tr><td><input type="hidden" value="<%= pid %>" name="pid"><a href="getPlantServlet?pid=<%= pid %>"><%= pid %></a></td>
                        <td><input type="number" value="<%= quantity %>" name="quantity"><td>
                        <td><input type="submit" value="update" name="action">
                            <input type="submit" value="delete" name="action"></td>

                    </tr>

                </form>
                <%  
                    }
                } else {

                %>
                <p>Your cart is Empty!</p>
                <% }%>
                <tr><td>Total money: </td></tr>
                <tr><td>Order Date:   </td></tr>
                <tr><td>Ship Date:  </td></tr>
            </table>
        </section>
                <section>
                    <form action="mainController" method="post">
                        <input type="submit" value="saveOrder" name="action" class="submitorder">
                        
                    </form>
                </section>
                <footer>
                    <%@include file="footer.jsp" %>
                </footer>
    </body>
</html>
