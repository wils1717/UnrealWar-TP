/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * 
 * @author c0533886
 */
@Path("/users")
@ApplicationScoped
public class UserREST {

    @Inject
    private UserController userController;

    /**
     * 
     * @return 
     */
    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(userController.getAllJson()).build();
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    @GET
    @Path("{username}")
    @Produces("application/json")
    public Response getByUsername(@PathParam("username") String username) {
        JsonObject json = userController.getByUsernameJson(username);
        if (json != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * 
     * @param id
     * @param json
     * @return 
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response edit(@PathParam("id") int id, JsonObject json) {
        JsonObject jsonUpdate = userController.updateScores(id,json.getInt("score"));
//        JsonObject jsonWithId = userController.editJson(id, json);
        if (jsonUpdate != null) {
            return Response.ok(jsonUpdate).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
