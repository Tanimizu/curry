/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;

/**
 *
 * @author g14958ym
 */
public class ProductDAO {

     public Product findProduct(String pid) {
        Connection conn = null;
        Product product = null;
        try {
            // JDBBCドライバを読み込む
            Class.forName("org.apache.derby.jdbc.ClientDriver");
           
            // データベースに接続
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db5807","db","db");
            
            // SELECT文を準備
            String sql = "SELECT PRODUCT_NAME, PRICE FROM PRODUCT WHERE PRODUCT_ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pid);
            
            // SELECT文を実行し、結果表を取得
            ResultSet rs  = pStmt.executeQuery();
  
            if (rs.next()) {
                // 結果表からデータを取得
                String productName = rs.getString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                
                product = new Product(pid,productName, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }   
        }
        return product; 
    }
}