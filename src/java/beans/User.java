
package beans;

import javax.json.Json;
import javax.json.JsonObject;

public class User {

    public int id;
    public String username;
    public String passhash;
    public int wins;
    public int loses;

    public User(int id, String username, String passhash, int wins, int loses) {
        this.id = id;
        this.username = username;
        this.passhash = passhash;
        this.wins = wins;
        this.loses = loses;
    }

    User() {
    }
    
    public User(JsonObject json) {        
        id = json.getInt("id", 0);
        username = json.getString("username", "");
        passhash = json.getString("passhash", "");
        wins = json.getInt("wins", 0);
        loses = json.getInt("loses", 0);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
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
                .add("loses", loses)
                .build();
    }

}
