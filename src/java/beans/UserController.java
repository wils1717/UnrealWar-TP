
package beans;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Random;
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

    private List<User> users;
    private static UserController instance = new UserController();
    Random ran = new Random();

    /**
     * No-arg constructor -- retrieves List from DB and sets up singleton
     */
    public UserController() {
        getUsersFromDB();
    }

    /**
     * Retrieve the List of UserController from the DB
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
                        rs.getString("passhash"),
                        rs.getInt("wins"),
                        rs.getInt("loses")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

    public void addToDb(User u) {
        try {
            String sql = "";
            Connection conn = DBUtils.getConnection();
            sql = "INSERT INTO users (id, username, passhash, wins, loses) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int id = ran.nextInt(2000000000);
            pstmt.setInt(1, id);
            pstmt.setString(2, u.getUsername());
            pstmt.setString(3, DBUtils.hash(u.getPasshash()));
            pstmt.setInt(4, u.getWins());
            pstmt.setInt(5, u.getLoses());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editToDb(User u) {
        try {
            String sql = "";
            Connection conn = DBUtils.getConnection();
            sql = "UPDATE users SET username = ?, passhash = ?, wins = ?, loses = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, DBUtils.hash(u.getPasshash()));
            pstmt.setInt(3, u.getWins());
            pstmt.setInt(4, u.getLoses());           
            pstmt.setInt(5, u.getId());          
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
    public static UserController getInstance() {
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

    public JsonObject addJson(JsonObject json) {
        User u = new User(json);
        addToDb(u);
        users.add(u);
        return u.toJson();
    }

    public JsonObject editJson(String username, JsonObject json) {
        User u = getByUsername(username);
        u.setUsername(json.getString("username", ""));
        u.setPasshash(json.getString("passhash", ""));
        u.setWins(json.getInt("wins", 0));
        u.setLoses(json.getInt("loses", 0));       
        editToDb(u);
        return u.toJson();
    }

    public void removeFromDb(User u) {
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
            pstmt.setString(1, u.getUsername());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean deleteByUsername(String username) {
        User u = getByUsername(username);
        if (u != null) {
            removeFromDb(u);
            users.remove(u);
            return true;
        } else {
            return false;
        }
    }
}
