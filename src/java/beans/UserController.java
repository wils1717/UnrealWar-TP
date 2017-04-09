package beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Named
@ApplicationScoped
public class UserController {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private boolean registered;
    private boolean deleted;
    private boolean passwordChanged;
    private List<User> users;
    private User instance = new User();

    public UserController() {
        instance = new User(0, "", "", 0, 0);
        oldPassword = null;
        newPassword = null;
        confirmPassword = null;
        registered = false;
        deleted = false;
        passwordChanged = false;
        getUsersFromDB();
    }

    public String clearFields() {
        oldPassword = null;
        newPassword = null;
        confirmPassword = null;
        registered = false;
        deleted = false;
        passwordChanged = false;
        getUsersFromDB();
        return "loginPage";
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }


    public boolean isRegistered() {
        return registered;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<User> getUsers() {
        users.sort(Comparator.comparing(User::getWins).reversed());
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getInstance() {
        return instance;
    }

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
    
    public JsonArray getAllJson() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (User u : users) {
            json.add(u.toJson());
        }
        return json.build();
    }

    public User getById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    
        public User getByUsername(String username) {
        for (User u : users) {
            if (u.getUsername() == username) {
                return u;
            }
        }
        return null;
    }

    public JsonObject getByIdJson(int id) {
        User u = getById(id);
        if (u != null) {
            return getById(id).toJson();
        } else {
            return null;
        }
    }
    
        public JsonObject getByUsernameJson(String username) {
        User u = getByUsername(username);
        if (u != null) {
            return getByUsername(username).toJson();
        } else {
            return null;
        }
    }

    public JsonObject editJson(int id, JsonObject json) {
        User u = getById(id);
        u.setUsername(json.getString("username", ""));
        u.setPasshash(json.getString("passhash", ""));
        u.setWins(json.getInt("wins", 0));
        u.setLosses(json.getInt("losses", 0));       
        editToDb(u);
        return u.toJson();
    }

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
     *
     *
     */
    public void addUser() {
        try (Connection conn = DBUtils.getConnection()) {
            if (instance.username.matches("^.*(?=.{4,10})(?=.*\\d)|(?=.*[a-zA-Z]).*$") && instance.passhash.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
                int counter = 1;
                Statement stmt = conn.createStatement();
                for (User u : users) {
                    counter++;
                }
                instance.id = counter;
                stmt.executeUpdate("INSERT INTO users VALUES (" + instance.id + ",'" + instance.username + "','" + DBUtils.hash(instance.passhash) + "', " + instance.wins + ", " + instance.losses + ")");
                registered = true;
                System.out.println(instance.username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editUserPassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        try (Connection conn = DBUtils.getConnection()) {
            if (newPassword.matches("^.*(?=.{4,10})(?=.*\\d)|(?=.*[a-zA-Z]).*$")) {
                if (newPassword.matches(confirmPassword)) {
                    String sql = "UPDATE users SET passhash = ? WHERE username = ? AND passhash = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, DBUtils.hash(newPassword));
                    pstmt.setString(2, username);
                    pstmt.setString(3, DBUtils.hash(oldPassword));
                    pstmt.executeUpdate();
                    passwordChanged = true;
                    getUsersFromDB();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteUser(String username, String password) {
        try {
            String passhash = DBUtils.hash(password);
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "' AND passhash = '" + passhash + "'");
            deleted = true;
            getUsersFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
