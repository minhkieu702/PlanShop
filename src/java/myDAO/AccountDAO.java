/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myDAO;

import data.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.DBUtils;

/**
 *
 * @author Acer
 */
public class AccountDAO {
    public static Account getAccount(String email, String password){
        Connection cn = null;
        Account acc = null;
        try{
            cn = src.DBUtils.makeConnection();
            if(cn!=null){
                String sql = "select accID, email, password, fullname, phone, status, role\n" +
"from dbo.Accounts\n" +
"where status=1 and email= ? and password=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs= pst.executeQuery();
                if(rs!=null && rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, FullName, Phone, Status, Role);
                    
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(cn!=null){
                try{
                    cn.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
    public static Account getAccount(String token){
        Connection cn = null;
        Account acc = null;
        try{
            cn = src.DBUtils.makeConnection();
            if(cn!=null){
                String sql = "select accID, email, password, fullname, phone, status, role\n" +
"from dbo.Accounts\n" +
"where status=1 and email= ? and password=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);

                ResultSet rs= pst.executeQuery();
                if(rs!=null && rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, FullName, Phone, Status, Role);
                    
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(cn!=null){
                try{
                    cn.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
    public static ArrayList<Account> getAccounts(){
        ArrayList<Account> list = new ArrayList<>();
        Account acc= null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"+
                        "from dbo.Accounts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs!=null){
                    while (rs.next()) {                        
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
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
     public static boolean updateAccountStatus(String email, int status){
        boolean kq = false;
        Account acc= null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "update dbo.Accounts\n" +
                                "set status = ?\n" +
                                "where email=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                int rs = pst.executeUpdate();
                kq = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
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
    public static boolean updateAccount(String email, String newFullname, String newPhone) {
        try {
            Connection cn = DBUtils.makeConnection();
            int kq = 0;
            String sql = "update dbo.Accounts\n" +
                    "set fullname = ?, phone = ?, password = ?\n" +
                    "where email = ? COLLATE Latin1_General_CS_AS";
            if (cn!=null) {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newFullname);
                pst.setString(2, newPhone);
                pst.setString(3, email);
                kq = pst.executeUpdate();
                cn.close();
            }
            if (kq!=0) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus,int newRole) throws Exception{
        Connection cn = DBUtils.makeConnection();
        int kq = 0;
        if(cn!=null ){
            String s = "insert into dbo.Accounts (email, password, fullname, phone, status, role)\n" +
"values (?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(s);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newSatus);
            pst.setInt(6, newRole);
            kq = pst.executeUpdate();
            cn.close();
        }
        if(kq!=0)return true;
        else return false;
    }
    public static boolean updateToken(String token, String email) {
        boolean kq = false;
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Accounts\n"
                        + "set token = ?\n"
                        + "where email=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
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
