/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author c0533886
 */
public class User {

    public int id;
    public String username;
    public String passhash;
    public int wins;
    public int losses;

    /**
     *
     * @param id
     * @param username
     * @param passhash
     * @param wins
     * @param losses
     */
    public User(int id, String username, String passhash, int wins, int losses) {
        this.id = id;
        this.username = username;
        this.passhash = passhash;
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Empty constructor to set default values
     */
    User() {
        id = 0;
        username = "";
        passhash = "";
        wins = 0;
        losses = 0;
    }

    /**
     *
     * @param json
     */
    public User(JsonObject json) {
        id = json.getInt("id", 0);
        username = json.getString("username", "");
        passhash = json.getString("passhash", "");
        wins = json.getInt("wins", 0);
        losses = json.getInt("losses", 0);
    }

    /**
     *
     * @return
     */
    public int getWins() {
        return wins;
    }

    /**
     *
     * @param wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     *
     * @return
     */
    public int getLosses() {
        return losses;
    }

    /**
     * 
     * @param losses 
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return 
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
     * @return 
     */
    public String getPasshash() {
        return passhash;
    }

    /**
     * 
     * @param passhash 
     */
    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

    /**
     * 
     * @return 
     */
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
