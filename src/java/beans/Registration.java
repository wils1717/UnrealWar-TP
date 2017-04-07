
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
    private int loses;
    private int wins;
    private boolean registered;
    private boolean deleted;
    Random ran = new Random();

    public Registration() {
        username = null;
        password = null;
        id = 0;
        wins = 0;
        loses = 0;
        registered = false;
        deleted = false;
    }

    public String clearFields() {
        username = null;
        password = null;
        id = 0;
        registered = false;
        deleted = false;
        return "loginPage";
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
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

    public boolean isRegistered() {
        return registered;
    }

    public boolean isDeleted() {
        return deleted;
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
            stmt.executeUpdate("INSERT INTO users VALUES (" + id + ",'" + username + "','" + passhash + "', " + wins + ", " + loses + ")");
            registered = true;
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(String username, String password) {
        try {
            String passhash = DBUtils.hash(password);
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "' AND passhash = '" + passhash + "'");
            deleted = true;
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
