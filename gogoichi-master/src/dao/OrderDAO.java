/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;


/**
 *
 * @author g14958ym
 */
public class OrderDAO {

    public boolean create(ArrayList<Order> o) {



        Connection conn = null;
        Date date = new Date();


        try {
           
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db5807", "db", "db");
          
            for (int i = 0; i < o.size(); i++) {
                String sql1 = "INSERT INTO ORDERS(USER_ID,PRODUCT_ID,QUANTITY,DELIVERY_TIME,DATE) VALUES(?,?,?,?,?)";
                PreparedStatement pStmt1 = conn.prepareStatement(sql1);
                pStmt1.setString(1, o.get(i).getUserId());
                pStmt1.setString(2, o.get(i).getProductId());
                pStmt1.setInt(3, o.get(i).getQuantity());
                pStmt1.setString(4, o.get(i).getDeliverytime());
                pStmt1.setString(5, o.get(i).getDate());

                int result = pStmt1.executeUpdate();
                
                if (result != 1) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
}
