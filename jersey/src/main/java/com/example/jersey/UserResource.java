package com.example.jersey;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/users")
public class UserResource {

    @POST
    public String createUser() {
        return "User created";
    }
}
