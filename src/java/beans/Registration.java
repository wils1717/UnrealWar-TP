/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

 
@Named
@SessionScoped
public class Registration implements Serializable {
private String username;
private String password;
private int id;
Random ran = new Random();




    public Registration() {
        username = null;
        password = null;
        id = 0;

    }

   public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Add a user to the DB
     *
     *
     *
     */
    public void addUser() {
        try (Connection conn = DBUtils.getConnection()) {
            String passhash = DBUtils.hash(password);
            Statement stmt = conn.createStatement();
            id = ran.nextInt(2000000000);
            stmt.executeUpdate("INSERT INTO users VALUES (" + id + ",'" + username + "','" + passhash + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String deleteUser(String username, String password) {
        try {
            String passhash = DBUtils.hash(password);
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "' AND passhash = '" + passhash + "'");
            return "loginPage";
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "loginPage";
    }
}
