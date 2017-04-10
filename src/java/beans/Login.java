/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class Login implements Serializable {
    
    /** 
     * Create variables 
     */
    private String username;
    private String password;
    private boolean loggedIn;
    private User currentUser;
    private List<User> users;
    private static User instance = new User();

    /**
     * Getter for List of users 
     * @return 
     */
    public List<User> getUsers() {
        return users;
    }
     /** 
      * Sets list of users
      * @param users 
      */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    /**
     * Gets called instance of User
     * @return 
     */
    public static User getInstance() {
        return instance;
    }
    
    public static void setInstance(User instance) {
        Login.instance = instance;
    }
    /**
     * Empty constructor for Login 
     */
    public Login() {
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
        getUsersFromDB();
    }

    /**
     * Constructor for logout that returns you to login page 
     * @return 
     */
    public String logout() {
        username = null;
        password = null;
        loggedIn = false;
        return "loginPage";
    }
    
    
    /**
     * Method for doLogin which tests for blank values
     * @param username
     * @param password
     * @return 
     */
    public String doLogin(String username, String password) {
        String retVal = null;
        if (username.equals("")) {
            retVal = "ERROR: Invalid Username";
        } else if (password.equals("")) {
            retVal = "ERROR: Invalid Password";
        }
        System.out.println(retVal);
        return retVal;
    }

    /**
     * Getter for username variable
     * @return 
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Setter for username variable 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Getter for password variable 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password variable 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Getter for loggedIn variable 
     * @return 
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Getter for currentUser variable 
     * @return 
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Method that tests for a user to be logged in before allowing them to access
     * the game.
     * If they aren't logged in or don't provide legitimate login credentials, 
     * they're returned to the login page.
     * @return 
     */
    public String login() {
        getUsersFromDB();
        String passhash = DBUtils.hash(password);
        for (User u : users) {
            if (username.equals(u.getUsername())
                    && passhash.equals(u.getPasshash())) {
                loggedIn = true;
                currentUser = u;
                return "game";
            }
        }
        currentUser = null;
        logout();
        loggedIn = false;
        return "loginPage";
    }

    /**
     * Method to connect to the database and retrieve users when called 
     * Records the wins and losses for the leader board
     */
    public void getUsersFromDB() {
        try (Connection conn = DBUtils.getConnection()) {
            users = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("passhash"),
                        rs.getInt("wins"),
                        rs.getInt("losses")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

}
