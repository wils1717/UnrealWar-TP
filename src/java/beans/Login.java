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

/**
 *
 * @author c0533886
 */
@Named
@SessionScoped
public class Login implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn;
    private boolean validUser;
    private User currentUser;
    private List<User> users;

    public Login() {
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
        validUser = true;
        getUsersFromDB();
    }

    /**
     *
     * @return
     */
    public String logout() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "loginPage";
    }

    /**
     *
     * @return
     */
    public String changePass() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "changePassPage";
    }

    /**
     *
     * @return
     */
    public String deleteUser() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "deleteUserPage";
    }

    /**
     *
     * @return
     */
    public String register() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "registrationPage";
    }

    /**
     *
     * @return
     */
    public String leaderboard() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "leaderboardsPage";
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     *
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     *
     * @return currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     *
     * @return validUser
     */
    public boolean isValidUser() {
        return validUser;
    }

    /**
     *
     * @param validPass
     */
    public void setValidUser(boolean validPass) {
        this.validUser = validPass;
    }

    /**
     *
     * @return loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sorts through the array list for credentials received if match then user
     * logs in
     *
     * @return either game.xhtml or loginPage.xhtml
     */
    public String login() {
        getUsersFromDB();
        String passhash = DBUtils.hash(password);
        for (User u : users) {
            if (username.equals(u.getUsername())
                    && passhash.equals(u.getPasshash())) {
                loggedIn = true;
                validUser = true;
                currentUser = u;
                return "game";
            }
        }
        validUser = false;
        currentUser = null;
        loggedIn = false;
        return "loginPage";
    }

    /**
     * Fetches all users from the database into an array list used for logging
     * in
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
            validUser = true;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

}
