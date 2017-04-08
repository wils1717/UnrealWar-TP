
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

    @OnMessage
    public void onUser(String user, Session session) {
        try {
            String output = "";
            JsonObject json = Json.createReader(new StringReader(user)).readObject();
            if (json.containsKey("getAll")) {
                output = userController.getAllJson().toString();
            } else if (json.containsKey("getByUsername")) {
                output = userController.getByUsernameJson(json.getString("getByUsername")).toString();
            } else if (json.containsKey("post")) {
                output = userController.addJson(json.getJsonObject("post")).toString();
            } else if (json.containsKey("put")) {
                String username = json.getJsonObject("put").getString("username");
                output = userController.editJson(username, json.getJsonObject("put")).toString();
            } else if (json.containsKey("delete")) {
                output = Json.createObjectBuilder()
                        .add("ok", userController.deleteByUsername(json.getString("delete")))
                        .build().toString();
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
