/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import myDAO.AccountDAO;
import myDAO.OrderDAO;

/**
 *
 * @author Acer
 */
public class TestConnection {
    public static void main(String[] args) {
        ArrayList<Order> list = OrderDAO.getOrders("test@gmail.com");
        for (Order order : list) {
            System.out.println(order);
        }
        
    }
}
