/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.jdo.*;
import ad5807.PMF;


public class RegistLogic {
    public void execute(Account account) {
    	 PersistenceManagerFactory factory = PMF.get();
         PersistenceManager manager = factory.getPersistenceManager();
         try {
             manager.makePersistent(data);
         } finally {
             manager.close();
         }
    }
}
