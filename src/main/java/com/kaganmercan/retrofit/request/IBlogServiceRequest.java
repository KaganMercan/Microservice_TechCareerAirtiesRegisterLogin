package com.kaganmercan.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author kaganmercan
 */
public interface IBlogServiceRequest {
    // REST             REQUEST
    // ----------       -------
    // @GetMapping      @GET
    // @PostMapping     @POST
    // @DeleteMapping   @DELETE
    // @PutMapping      @PUT

    // @PathVariable    @Path
    // @RequestBody     @Body

    // ResponseEntity   Call
    // BlogDto         JsonElement

    // CREATE
    @POST("/api/v1/blog/create")
    Call<JsonElement> createBlog(@Body JsonElement jsonElement);

    // LIST
    @GET("/api/v1/blog/list")
    Call<List<JsonElement>> listBlog();

    // FIND
    @GET("/api/v1/blog/find/{id}")
    Call<JsonElement> findBlog(@Path("id") Long id);

    // UPDATE
    @PUT("/api/v1/blog/update/{id}")
    Call<JsonElement> updateBlog(@Path("id") Long id, @Body JsonElement jsonElement);

    // DELETE
    @DELETE("/api/v1/blog/delete/{id}")
    Call<JsonElement> deleteBlog(@Path("id") Long id);
}
