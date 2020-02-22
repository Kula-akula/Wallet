package kg.wallet.service;

import kg.wallet.dao.UserDao;
import kg.wallet.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserService {
    private UserDao userDao = new UserDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers_Json() {
        return userDao.getUsers();
    }
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") Integer userId) {
        return userDao.getUserById(userId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user){
        return userDao.updateUser(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user){
        return userDao.createUser(user);
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("userId") int userId){
        return userDao.deleteById(userId) ? "deleted" : "not deleted";
    }
}
