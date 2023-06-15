<%-- 
    Document   : OrderDetail
    Created on : Feb 15, 2023, 8:30:30 PM
    Author     : Acer
--%>

<%@page import="myDAO.OrderDAO"%>
<%@page import="data.OrderDetail"%>
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
            if(name==null){
            %>
            <p><font color="red">you must be login to view personal page</p>
            <%} else{        
                %>
            <header>
                <%@include file="header_LoginUser.jsp" %>
            </header>
            <section>
                <h3>Welcome <%= name%> come back </h3>
                <h3>Logout</h3>
                <a href="personalPage.jsp"> View All Orders</a>
            </section>
            <section>
            <% String orderid = request.getParameter("orderid");
                   if(orderid!=null){
                    int OrderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list= OrderDAO.getOrderDetail(OrderID);                  
                    if(list!= null && !list.isEmpty()){
                    int money=0;
                    for(OrderDetail detail: list){ %>
                    <table class="order">
                        <tr>
                            <td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Quantity</td>   
                        </tr>
                        <tr>
                            <td><%= detail.getOrderID() %></td><td><%= detail.getPlantID() %></td>
                            <td><%= detail.getPlantName() %></td>
                            <td><img src='<%= detail.getImgPath() %>' class='plantimg'/><br/></td>
                            <td><%= detail.getQuantity() %></td>
                            <% money = money + detail.getPrice() * detail.getQuantity(); %>
                        </tr>
                    </table>
                            <% } //end dòng for %>
            <h3> Total money: <%= money %></h3>
            <% } //end dòng if
                else{
            %>                               
            <p>you don't have any order</p>
            <% }
                    } //end dòng if
}
            %>
            </section>
       <footer>
                <%@include file="footer.jsp" %>
            </footer>
    </body>
</html>
