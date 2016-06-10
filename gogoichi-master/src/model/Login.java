/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author g14958ym
 */
public class Login implements Serializable {
    private String userId, pass;
    public Login() {}
    public Login (String userId, String pass) {
        this.userId = userId;
        this.pass = pass;
    }    
    public String getUserId() { return userId; }
    public String getPass() { return pass; }
}