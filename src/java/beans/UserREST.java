
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

@Path("/users")
@ApplicationScoped
public class UserREST {

    @Inject
    private UserController userController;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(userController.getAllJson()).build();
    }
    
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

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response edit(@PathParam("id") int id, JsonObject json) {
        JsonObject jsonWithId = userController.editJson(id, json);
        if (jsonWithId != null) {
            return Response.ok(jsonWithId).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
