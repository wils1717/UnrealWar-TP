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

    private String username;
    private String password;
    private boolean loggedIn;
    private boolean validUser;
    private User currentUser;
    private List<User> users;
    private static User instance = new User();

    public Login() {
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
        validUser = true;
        getUsersFromDB();
    }

    public String logout() {
        username = null;
        password = null;
        loggedIn = false;
        validUser = true;
        return "loginPage";
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

    public boolean isValidUser() {
        return validUser;
    }

    public void setValidUser(boolean validPass) {
        this.validUser = validPass;
    }

    public static void setInstance(User instance) {
        Login.instance = instance;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

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
            else{
                validUser = false;
            }   
        }
        if (validUser){
            logout();
        }
        currentUser = null;
        loggedIn = false;
        return "loginPage";
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets User List as Empty
            users = new ArrayList<>();
        }
    }

}
