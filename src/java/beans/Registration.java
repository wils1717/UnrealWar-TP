package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private int id;
    private int losses;
    private int wins;
    private boolean registered;
    private boolean deleted;
    private boolean passwordChanged;
    Random ran = new Random();
    private List<User> users;
    private static User instance = new User();

    public Registration() {
        username = null;
        password = null;
        oldPassword = null;
        newPassword = null;
        confirmPassword = null;
        id = 0;
        wins = 0;
        losses = 0;
        registered = false;
        deleted = false;
        passwordChanged = false;
    }

    public String clearFields() {
        username = null;
        password = null;
        oldPassword = null;
        newPassword = null;
        confirmPassword = null;
        id = 0;
        registered = false;
        deleted = false;
        passwordChanged = false;
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

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static User getInstance() {
        return instance;
    }

    public static void setInstance(User instance) {
        Registration.instance = instance;
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

    /**
     * Add a user to the DB
     *
     * 
     *
     */
    public void addUser() {
        try (Connection conn = DBUtils.getConnection()) {
            getUsersFromDB();
            if (username.matches("^.*(?=.{4,10})(?=.*\\d)|(?=.*[a-zA-Z]).*$") && password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
                int counter = 1;
                String passhash = DBUtils.hash(password);
                Statement stmt = conn.createStatement();
                for (User u : users) {
                    counter++;
                }
                id = counter;
                stmt.executeUpdate("INSERT INTO users VALUES (" + id + ",'" + username + "','" + passhash + "', " + wins + ", " + losses + ")");
                registered = true;
                System.out.println(username);

            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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
            getUsersFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
