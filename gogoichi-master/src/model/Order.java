/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author g14958ym
 */
public class Order implements Serializable {

    private String userId, productId, deliveryTime;
    private int quantity;
    private String date;

    public Order() {
    }

    public Order(String userId, String productId, int quantity, String deliveryTime, String date) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.deliveryTime = deliveryTime;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

     public String getDeliverytime() {
        return deliveryTime;
    }
    
    public String getDate() {
        return date;
    }
}
