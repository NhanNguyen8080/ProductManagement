/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author dell
 */
public class Product implements Serializable{
    private String productID, productName, status;
    private double unitPrice;
    private int quantity;

    public Product(String productID, String productName, String status, double unitPrice, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.status = status;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-15s|%-13s|%-10.2f|%-8d|",
                                productID, productName, status, unitPrice, quantity);
    }
    
    
}
