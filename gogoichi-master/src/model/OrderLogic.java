/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.OrderDAO;
import java.util.ArrayList;

/**
 *
 * @author g14958ym
 */
public class OrderLogic {
    public void execute(ArrayList<Order> o) {
        OrderDAO dao = new OrderDAO();
        dao.create(o);
    }
}
