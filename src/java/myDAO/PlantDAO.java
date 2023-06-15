/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myDAO;

import data.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlant(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn =null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn!= null && searchby!= null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where Plants.PName like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+keyword+"%");
                ResultSet rs = pst.executeQuery();
                if (rs!= null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String des = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateID = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgPath, des, status, cateID, cateName);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static Plant getPlant(int pid){
        Connection cn = null;
        Plant p = null;
        try {
            cn = src.DBUtils.makeConnection();
            if (cn!=null) {
                String sql = "select PID, PName, description, status, Plants.CateID, imgPath, price, CateName\n" +
"from dbo.Plants, Categories\n" +
"where Plants.CateID=Categories.CateID and PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if(rs!=null && rs.next()){
                    int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        p = new Plant(id, name, price, imgpath, description, status, cateid,cateName);
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
        return p;
    }
}
