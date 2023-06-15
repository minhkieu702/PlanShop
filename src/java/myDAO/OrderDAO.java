/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myDAO;

import data.Order;
import data.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import src.DBUtils;

/**
 *
 * @author Acer
 */
public class OrderDAO {
public static ArrayList<Order> getOrder(String email, Date from, Date to) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n"
                        + "where OrdDate BETWEEN ? AND ? "
                        + " and AccID = (select Accounts.accID from Accounts where email=? COLLATE Latin1_General_CS_AS)";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setDate(1, from);
                pst.setDate(2, to);
                pst.setString(3, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int oid = rs.getInt("OrderID");
                        String oDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipDate");
                        int sta = rs.getInt("status");
                        int aID = rs.getInt("AccID");
                        list.add(new Order(oid, oDate, shipDate, sta, aID));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n"
                        + "where AccID = (select Accounts.accID from Accounts where email=? COLLATE Latin1_General_CS_AS) ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int ordID = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order ord = new Order(ordID, ordDate, shipDate, status, accID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
public static ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int ordID = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order ord = new Order(ordID, ordDate, shipDate, status, accID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static ArrayList<OrderDetail> getOrderDetail(int OrderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, PID, PName, price, imgPath, quantity\n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID=? and OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, OrderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail ordDetail = new OrderDetail(detailID, OrderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(ordDetail);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public static boolean insertOrder(String email, HashMap<String,Integer> cart){
        Connection cn = null;
        boolean result = false;
        try {
            cn=src.DBUtils.makeConnection();
            if (cn!=null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);//off autocommit
                //get account id by email
                String s = "select accID from Accounts  where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs!=null && rs.next()) {
                    accid = rs.getInt("accid");
                }
                System.out.println("accountid:"+accid);
                Date d = new java.sql.Date(System.currentTimeMillis());
                System.out.println("order date: "+d);
                s = "insert into Orders(OrdDate, status, AccID)\n"+
                    "values(?,?,?)";
            pst = cn.prepareStatement(s);
            pst.setDate(1, (java.sql.Date) d);
            pst.setInt(2, 1);
            pst.setInt(3, accid);
                pst.executeUpdate();
                //get order id that is the largest number
                s = "select top 1 orderID\n"
                        + "from Orders \n"
                        + "order by orderID desc";
                pst = cn.prepareStatement(s);
                rs = pst.executeQuery();
                if(rs!=null && rs.next()) orderid = rs.getInt("OrderID");
                //insert order details
                System.out.println("orderid: "+orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    s = "insert orderDetails values(?,?,?)";
                    pst = cn.prepareStatement(s);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("khong chen order duoc");
            }
        } catch (Exception e) {
            try {
                if (cn!=null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result= false;
        }
        finally{
            if (cn!=null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static boolean updateOrderStatus(int ID, int status) {
        boolean kq = false;
        Connection cn = null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set status = ?\n"
                        + "where OrderID=? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, ID);
                int rs = pst.executeUpdate();
                kq = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return kq;
    }
}
