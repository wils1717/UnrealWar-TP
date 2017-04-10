
package beans;

import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.constraints.Size;

public class User {

    public int id;
    public String username;
    public String passhash;
    public int wins;
    public int losses;

    public User(int id, String username, String passhash, int wins, int losses) {
        this.id = id;
        this.username = username;
        this.passhash = passhash;
        this.wins = wins;
        this.losses = losses;
    }

    User() {
    }
    
    public User(JsonObject json) {        
        id = json.getInt("id", 0);
        username = json.getString("username", "");
        passhash = json.getString("passhash", "");
        wins = json.getInt("wins", 0);
        losses = json.getInt("losses", 0);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasshash() {
        return passhash;
    }

    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("username", username)
                .add("passhash", passhash)
                .add("wins", wins)
                .add("losses", losses)
                .build();
    }

}
