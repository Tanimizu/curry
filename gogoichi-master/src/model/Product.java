/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.Serializable;
/**
 *
 * @author g14958ym
 */

public class Product implements Serializable{
  private String productId,productName;
  private int price;
  
  
  
  public Product() { }
  public Product(String productId, String productName, int price) {
    this.productId = productId;
    this.productName = productName;
    this.price = price;
  }

  public String getProductId() { return productId; }
  public String getProductName() { return productName; }
  public int getPrice() { return price; }
}
