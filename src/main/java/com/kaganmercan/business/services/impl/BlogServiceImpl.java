package com.kaganmercan.business.services.impl;

import com.google.gson.JsonElement;
import com.kaganmercan.business.services.IBlogServices;
import com.kaganmercan.retrofit.RetrofitCommonGenerics;
import com.kaganmercan.retrofit.request.IBlogServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaganmercan
 */
// Lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class BlogServiceImpl implements IBlogServices {
    // Injection
    private final IBlogServiceRequest blogServiceRequest;

    // CREATE
    @Override
    public JsonElement createBlog(JsonElement jsonElement) {
        // blogServiceRequest calls /api/v1/blog/create from Blog Microservice...
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.createBlog(jsonElement));
    }

    // LIST
    @Override
    public List<JsonElement> listBlog() {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.listBlog());
    }

    // FIND
    @Override
    public JsonElement findBlog(Long id) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.findBlog(id));
    }

    // UPDATE
    @Override
    public JsonElement updateBlog(Long id, JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.updateBlog(id, jsonElement));
    }

    // DELETE
    @Override
    public void deleteBlog(Long id) {
        RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.deleteBlog(id));
    }
}
