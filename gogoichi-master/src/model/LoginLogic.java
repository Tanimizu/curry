/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccountDAO;

public class LoginLogic {
    public boolean excecute (Login login) {
        AccountDAO dao = new AccountDAO();
        Account account = dao.findByLogin(login);
        return account != null;
    }
}
