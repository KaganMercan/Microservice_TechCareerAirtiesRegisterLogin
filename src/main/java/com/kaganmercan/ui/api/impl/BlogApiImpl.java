package com.kaganmercan.ui.api.impl;

import com.google.gson.JsonElement;
import com.kaganmercan.business.services.IBlogServices;
import com.kaganmercan.error.ApiResult;
import com.kaganmercan.ui.api.IBlogApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author kaganmercan
 */
// Lombok
@RequiredArgsConstructor

@RestController
@RequestMapping("/gateway/blog")
public class BlogApiImpl implements IBlogApi {
    // Injection
    private final IBlogServices blogService;
    private static final String PATH = "/gateway/blog";

    // CREATE
    // http://localhost:1111/gateway/blog
    @Override
    @PostMapping
    public ApiResult createBlog(@RequestBody JsonElement jsonElement) {
        blogService.createBlog(jsonElement);
        return new ApiResult(200, "Blog post created", PATH);
    }

    // LIST
    // http://localhost:1111/gateway/blog
    @Override
    @GetMapping
    public ResponseEntity<List<?>> listBlog() {
        blogService.listBlog();
        return ResponseEntity.ok(blogService.listBlog());
    }

    // FIND
    // http://localhost:1111/gateway/blog/1
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findBlog(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(blogService.findBlog(id));
    }

    // UPDATE
    // http://localhost:1111/gateway/blog/1
    @Override
    @PutMapping("/{id}")
    public ApiResult updateBlog(@PathVariable(name = "id") Long id, @RequestBody JsonElement jsonElement) {
        blogService.updateBlog(id, jsonElement);
        return new ApiResult(200, "Blog post updated", PATH);
    }

    // DELETE
    // http://localhost:1111/gateway/blog/1
    @Override
    @DeleteMapping("/{id}")
    public ApiResult deleteBlog(@PathVariable(name = "id") Long id) {
        blogService.deleteBlog(id);
        return new ApiResult(200, "Blog post deleted", PATH);
    }
}
