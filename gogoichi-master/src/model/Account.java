/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
/**
 *
 * @author g14958ym
 */

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Account  implements Serializable{

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String userId;
     
    @Persistent
    private String pass;
     
    @Persistent
    private String name;
     
    @Persistent
    private String address;
     
    @Persistent
    private String phonenumber;
	
  public Account() { }
  public Account(String userId, String pass, String name, String address, String phonenumber) {
    this.userId = userId;
    this.pass = pass;
    this.name = name;
    this.address = address;
    this.phonenumber = phonenumber;
  }

  public String getUserId() { return userId; }
  public String getPass() { return pass; }
  public String getName() { return name; }
  public String getAddress() { return address; }
  public String getPhoneNumber() { return phonenumber; }

}