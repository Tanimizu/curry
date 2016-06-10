/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
    public Account findByLogin(Login login) {
        Connection conn = null;
        Account account = null;
        try {
            // JDBBCドライバを読み込む
            Class.forName("org.apache.derby.jdbc.ClientDriver");
           
            // データベースに接続
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db5807","db","db");
            
            // SELECT文を準備
            String sql = "SELECT USER_ID, PASS, NAME, ADDRESS, PHONENUMBER FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, login.getUserId());
            pStmt.setString(2, login.getPass());
            
            // SELECT文を実行し、結果表を取得
            ResultSet rs  = pStmt.executeQuery();
  
           // 一致したユーザーが存在した場合、そのユーザーを表すAccountインスタンスを生成
            if (rs.next()) {
                // 結果表からデータを取得
                String userId = rs.getString("USER_ID");
                String pass = rs.getString("PASS");
                String name = rs.getString("NAME");
                String address = rs.getString("ADDRESS");
                String phonenumber = rs.getString("PHONENUMBER");
                
                account = new Account(userId, pass, name, address, phonenumber);
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
        // 見つかったユーザーまたはnullを返す
        return account; 
    }
    public boolean create(Account account) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db5807","db","db");
            
            String sql = "INSERT INTO ACCOUNT(USER_ID,PASS,NAME,ADDRESS,PHONENUMBER) VALUES(?,?,?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, account.getUserId());
            pStmt.setString(2, account.getPass());
            pStmt.setString(3, account.getName());
            pStmt.setString(4, account.getAddress());
            pStmt.setString(5, account.getPhoneNumber());
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try{
                    conn.close();
                } catch (SQLException e) {
                e.printStackTrace();
                }
            }      
         }
         return true;
    }   
}

