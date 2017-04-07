/*
 * Copyright 2016 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package beans;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
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
