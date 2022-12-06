package com.kaganmercan.ui.api;

import com.google.gson.JsonElement;
import com.kaganmercan.error.ApiResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDailyApi {
    // REST             REQUEST
    // ----------       -------
    // @GetMapping      @GET
    // @PostMapping     @POST
    // @DeleteMapping   @DELETE
    // @PutMapping      @PUT

    // @PathVariable    @Path
    // @RequestBody     @Body

    // ResponseEntity   Call
    // DailyDto         JsonElement


    // CREATE
    ApiResult createDaily(JsonElement jsonElement);

    // LIST
    ResponseEntity<List<?>> listDaily();

    // FIND
    ResponseEntity<?> findDaily(Long id);

    // UPDATE
    ApiResult updateDaily(Long id, JsonElement jsonElement) throws Throwable;

    // DELETE
    ApiResult deleteDaily(Long id);
}
