/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * 
 * @author c0533886
 */
@Named
@ApplicationScoped
public class UserController implements Serializable {

    private String newPassword;
    private String confirmPassword;
    private boolean registered;
    private boolean deleted;
    private boolean passwordChanged;
    private int incorrectPassChange;
    private boolean incorrectPassDelete;
    private List<User> users;
    private User instance = new User();

    /**
     * constructor to set default values
     */
    public UserController() {
        instance = new User(0, "", null, 0, 0);
        newPassword = null;
        confirmPassword = null;
        registered = false;
        deleted = false;
        passwordChanged = false;
        incorrectPassChange = 0;
        incorrectPassDelete = false;
        getUsersFromDB();
    }

    /**
     * Clears all boolean fields
     * @return loginPage.xhtml
     */
    public String clearFields() {
        newPassword = null;
        confirmPassword = null;
        registered = false;
        deleted = false;
        passwordChanged = false;
        incorrectPassChange = 0;
        incorrectPassDelete = false;
        getUsersFromDB();
        return "loginPage";
    }

    /**
     * 
     * @return confrimPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * 
     * @param confirmPassword 
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * 
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 
     * @param newPassword 
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 
     * @return incorrectPassChange
     */
    public int isIncorrectPassChange() {
        return incorrectPassChange;
    }

    /**
     * 
     * @param incorrectPassChange 
     */
    public void setIncorrectPassChange(int incorrectPassChange) {
        this.incorrectPassChange = incorrectPassChange;
    }

    /**
     * 
     * @return incorrectPassDelete
     */
    public boolean isIncorrectPassDelete() {
        return incorrectPassDelete;
    }

    /**
     * 
     * @param incorrectPassDelete 
     */
    public void setIncorrectPassDelete(boolean incorrectPassDelete) {
        this.incorrectPassDelete = incorrectPassDelete;
    }

    /**
     * 
     * @return passwordChanged
     */
    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    /**
     * 
     * @param passwordChanged 
     */
    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    /**
     * 
     * @return registered
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * 
     * @param registered 
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * 
     * @param deleted 
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 
     * @return deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * 
     * @return users
     */
    public List<User> getUsers() {
        users.sort(Comparator.comparing(User::getWins).reversed());
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
     * @return instance
     */
    public User getInstance() {
        return instance;
    }

    /**
     * 
     * @param instance 
     */
    public void setInstance(User instance) {
        this.instance = instance;
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
     * gets all users in the list by username for json builder
     * @return 
     */
    public JsonArray getAllJson() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (User u : users) {
            json.add(u.toJson());
        }
        return json.build();
    }

    /**
     * gets a user in the list by id
     * @param id
     * @return either a User or null
     */
    public User getById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    /**
     * gets a user in the list by username
     * @param username
     * @return 
     */
    public User getByUsername(String username) {
        for (User u : users) {
            if (u.getUsername() == username) {
                return u;
            }
        }
        return null;
    }

    /**
     * gets a user in the list by id for json
     * @param id
     * @return 
     */
    public JsonObject getByIdJson(int id) {
        User u = getById(id);
        if (u != null) {
            return getById(id).toJson();
        } else {
            return null;
        }
    }

    /**
     * gets a user in the list by username for json
     * @param username
     * @return 
     */
    public JsonObject getByUsernameJson(String username) {
        User u = getByUsername(username);
        if (u != null) {
            return getByUsername(username).toJson();
        } else {
            return null;
        }
    }

    /**
     * edits a user in the list by id for json
     * @param id
     * @param json
     * @return 
     */
    public JsonObject editJson(int id, JsonObject json) {
        User u = getById(id);
        u.setUsername(json.getString("username", ""));
        u.setPasshash(json.getString("passhash", ""));
        u.setWins(json.getInt("wins", 0));
        u.setLosses(json.getInt("losses", 0));
        editToDb(u);
        return u.toJson();
    }

    /**
     * Retrieves all users from the database and places them in an arrayList
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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

    /**
     * edit user method for json call when updating the score of a user
     * @param u 
     */
    public void editToDb(User u) {
        try {
            String sql = "";
            Connection conn = DBUtils.getConnection();
            sql = "UPDATE users SET username = ?, passhash = ?, wins = ?, losses = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getPasshash());
            pstmt.setInt(3, u.getWins());
            pstmt.setInt(4, u.getLosses());
            pstmt.setInt(5, u.getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add a user to the DB
     *
     */
    public void addUser() {
        try (Connection conn = DBUtils.getConnection()) {
            getUsersFromDB();
            if (!instance.username.isEmpty() && instance.passhash.matches("^.*(?=.{4,12})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
                int counter = 1;
                Statement stmt = conn.createStatement();
                for (User u : users) {
                    counter++;
                }
                instance.id = counter;
                stmt.executeUpdate("INSERT INTO users VALUES (" + instance.id + ",'" + instance.username + "','" + DBUtils.hash(instance.passhash) + "', " + instance.wins + ", " + instance.losses + ")");
                registered = true;
                System.out.println(counter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * edits the password of a particular user if all requirements are met
     * @param username
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword 
     */
    public void editUserPassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        try (Connection conn = DBUtils.getConnection()) {
            getUsersFromDB();
            for (User u : users) {
                if (instance.username.equals(u.getUsername())
                        && DBUtils.hash(instance.passhash).equals(u.getPasshash())) {
                    if (newPassword.matches("^.*(?=.{4,10})(?=.*\\d)|(?=.*[a-zA-Z]).*$") && !oldPassword.equals(newPassword)) {
                        if (newPassword.matches(confirmPassword)) {
                            String sql = "UPDATE users SET passhash = ? WHERE username = ? AND passhash = ?";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, DBUtils.hash(newPassword));
                            pstmt.setString(2, username);
                            pstmt.setString(3, DBUtils.hash(oldPassword));
                            pstmt.executeUpdate();
                            passwordChanged = true;
                            incorrectPassChange = 2;
                            getUsersFromDB();
                        }
                    }
                } else {
                    passwordChanged = false;
                    incorrectPassChange = 1;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * used for updating the scores from in game calls
     * @param id
     * @param score
     * @return 
     */
    public JsonObject updateScores(int id, int score) {

        User u = getById(id);
        if (score > 0) {
            u.setWins(u.getWins() + 1);
        } else {
            u.setLosses(u.getLosses() + 1);
        }
        editToDb(u);
        return u.toJson();
    }

    /**
     * Delete a user if credentials match the given fields of a user within the list
     * @param username
     * @param password 
     */
    public void deleteUser(String username, String password) {
        try {
            for (User u : users) {
                if (instance.username.equals(u.getUsername())
                        && DBUtils.hash(instance.passhash).equals(u.getPasshash())) {
                    String passhash = DBUtils.hash(password);
                    Connection conn = DBUtils.getConnection();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "' AND passhash = '" + passhash + "'");
                    deleted = true;
                    incorrectPassDelete = false;
                    getUsersFromDB();
                } else {
                    incorrectPassDelete = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Used for changing passwords
     * Checker for form validation on passwords
     * @param event 
     */
    public void validatePassword(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get oldPassword
        UIInput uiInputOldPassword = (UIInput) components.findComponent("oldPassword");
        String oldPassword = uiInputOldPassword.getLocalValue() == null ? ""
                : uiInputOldPassword.getLocalValue().toString();

        // get newPassword
        UIInput uiInputNewPassword = (UIInput) components.findComponent("newPassword");
        String newPassword = uiInputNewPassword.getLocalValue() == null ? ""
                : uiInputNewPassword.getLocalValue().toString();
        String passwordId = uiInputNewPassword.getClientId();

        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (newPassword.isEmpty() || confirmPassword.isEmpty() || oldPassword.isEmpty()) {
            return;
        }

        if (!newPassword.equals(confirmPassword)) {

            FacesMessage msg1 = new FacesMessage("Password must match confirm password");
            msg1.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg1);
            fc.renderResponse();
        }

        if (oldPassword.equals(newPassword)) {

            FacesMessage msg2 = new FacesMessage("Password must be different from old one");
            msg2.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg2);
            fc.renderResponse();
        }

    }

}
