package org.example.Resources
import org.example.model.User
import org.example.services.UserService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("users")
class UserResource @Inject constructor(private val userService: UserService) {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(user: User):Response{
        val res = userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Response {
        val users = userService.getAllUsers() // Call to service to get all users
        return Response.ok(users).build()
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserByUserId(@PathParam("userId") userId: String): Response{
        val user = userService.getUserByUserId(userId)

        return Response.ok(user).build();
    }
}