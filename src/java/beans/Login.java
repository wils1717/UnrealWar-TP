
package beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;


@Named
@SessionScoped
public class Login implements Serializable {
private String username;
private String password;
private boolean loggedIn;
private User currentUser;

    public Login() {
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
    }
    
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
    String passhash = DBUtils.hash(password);
    
    for (User u : UserController.getInstance().getUsers()) {
        System.out.println(u.getPasshash() + " " + passhash);
        if(username.equals(u.getUsername()) 
         && passhash.equals(u.getPasshash())) {
        loggedIn = true;
        currentUser = u;
        return "game";
        }
    }
    currentUser = null;
        loggedIn = false;
        return "loginPage";
    }
    
}
