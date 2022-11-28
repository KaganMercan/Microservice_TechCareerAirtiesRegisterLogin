package com.kaganmercan.business.services;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * @author kaganmercan
 */
public interface IBlogServices {
    // CREATE
    JsonElement createBlog(JsonElement jsonElement);

    // LIST
    List<JsonElement> listBlog();

    // FIND
    JsonElement findBlog(Long id);

    // UPDATE
    JsonElement updateBlog(Long id, JsonElement jsonElement);

    // DELETE
    void deleteBlog(Long id);
}
