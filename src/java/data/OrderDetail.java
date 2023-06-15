/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Acer
 */
public class OrderDetail {
    private int OrderDetailID;
    private int OrderID;
    private int plantID;
    private String plantName;
    private int price;
    private String ImgPath;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int OrderDetailID, int OrderID, int plantID, String plantName, int price, String ImgPath, int quantity) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.plantID = plantID;
        this.plantName = plantName;
        this.price = price;
        this.ImgPath = ImgPath;
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String ImgPath) {
        this.ImgPath = ImgPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
