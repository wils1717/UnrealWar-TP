/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.json.Json;
import javax.json.JsonObject;

public class User {
    
    /**
     * Declare variables 
     */
    public int id;
    public String username;
    public String passhash;
    public int wins;
    public int losses;
    
    /**
     * Class constructor
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

    User() {
    }
    
    /**
     * Sets User variables into Json object
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
     * Getter for wins variable 
     * @return 
     */
    public int getWins() {
        return wins;
    }

    /**
     * Setter for wins variable 
     * @param wins 
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
    
    /**
     * Getter for losses variable 
     * @return 
     */
    public int getLosses() {
        return losses;
    }

    /** 
     * Setter for losses variable 
     * @param losses 
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }
    
    /**
     * Getter for int variable 
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Setter for int variable 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for username variable 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username variable 
     * @param username 
     */    
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for passhash variable 
     * @return 
     */
    public String getPasshash() {
        return passhash;
    }

    /**
     * Setter for passhash variable 
     * @param passhash 
     */
    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }
    
    /**
     * Create an instance of a Json Object which adds in variables from 
     * User class
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
