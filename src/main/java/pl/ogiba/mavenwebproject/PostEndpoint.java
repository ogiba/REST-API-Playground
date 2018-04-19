/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ogiba.mavenwebproject;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.ogiba.mavenwebproject.models.Post;

/**
 *
 * @author ogiba
 */
@Path("/posts")
public class PostEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@QueryParam("limit") int limit) {
        final String data = provideMockedItems(null);
        return data;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getSingle(@PathParam("id") int id) {
        Post post = new Post(id, "test", "content");
        GenericEntity<Post> list = new GenericEntity<Post>(post) {};
        return Response.ok(list).build();
    }
    
    private String provideMockedItems(String newLimit) {
        int limit = 10;

        if (newLimit != null) {
            try {
                limit = Integer.parseInt(newLimit);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        ArrayList<Post> posts = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            Post post = new Post(i, "Test", "Test content");
            posts.add(post);
        }

        Gson gson = new Gson();
        return gson.toJson(posts);
    }
}
