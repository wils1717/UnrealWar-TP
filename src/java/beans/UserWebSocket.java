/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socket")
@ApplicationScoped
public class UserWebSocket {

    @Inject
    UserController userController;

    /**
     * 
     * @param user
     * @param session 
     */
    @OnMessage
    public void onUser(String user, Session session) {
        try {
            String output = "";
            JsonObject json = Json.createReader(new StringReader(user)).readObject();
            if (json.containsKey("getAll")) {
                output = userController.getAllJson().toString();
            } else if (json.containsKey("getById")) {
                output = userController.getByIdJson(json.getInt("getById")).toString();
            } else if (json.containsKey("put")) {
                int id = json.getJsonObject("put").getInt("id");
                output = userController.editJson(id, json.getJsonObject("put")).toString();
            } else {
                output = Json.createObjectBuilder()
                        .add("error", "Invalid Request")
                        .add("original", json)
                        .build().toString();
            }
            session.getBasicRemote().sendText(output);
        } catch (IOException ex) {
            Logger.getLogger(UserWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
