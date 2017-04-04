/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * SORRY MARK IS A NOOB
 * @author c0121833
 */
@Named
@ApplicationScoped
public class Users {

    private List<User> users;
    private static Users instance = new Users();

    /**
     * No-arg constructor -- retrieves List from DB and sets up singleton
     */
    public Users() {
        getUsersFromDB();
    }

    /**
     * Retrieve the List of Users from the DB
     */
    private void getUsersFromDB() {
        try (Connection conn = DBUtils.getConnection()) {
            users = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("passhash")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

    /**
     * Retrieve the List of User objects
     *
     * @return the List of User objects
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Retrieve the known instance of this class
     *
     * @return the known instance of this class
     */
    public static Users getInstance() {
        return instance;
    }

    /**
     * Retrieve a specific username by ID
     *
     * @param id the ID to search for
     * @return the username
     */
    public String getUsernameById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u.getUsername();
            }
        }
        return null;
    }

    /**
     * Retrieve a specific user ID by username
     *
     * @param username the username to search for
     * @return the user ID
     */
    public int getUserIdByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u.getId();
            }
        }
        return -1;
    }

    /**
     * Add a user to the DB
     *
     * @param username the username
     * @param password the password
     */
    public void addUser(String username, String password) {
        try (Connection conn = DBUtils.getConnection()) {
            String passhash = DBUtils.hash(password);
            String sql = "INSERT INTO users (username, passhash) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, passhash);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        getUsersFromDB();
    }
}
