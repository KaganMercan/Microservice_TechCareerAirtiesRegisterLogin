package com.kaganmercan.ui.api;

import com.google.gson.JsonElement;
import com.kaganmercan.error.ApiResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author kaganmercan
 */
public interface IBlogApi {
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
    ApiResult createBlog(JsonElement jsonElement);

    // LIST
    ResponseEntity<List<?>> listBlog();

    // FIND
    ResponseEntity<?> findBlog(Long id);

    // UPDATE
    ApiResult updateBlog(Long id, JsonElement jsonElement);

    // DELETE
    ApiResult deleteBlog(Long id);
}
