package com.kaganmercan.business.services;

import com.google.gson.JsonElement;

import java.util.List;

public interface IDailyService {
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
    JsonElement createDaily(JsonElement jsonElement);

    // LIST
    List<JsonElement> listDaily();

    // FIND
    JsonElement findDaily(Long id);

    // UPDATE
    JsonElement updateDaily(Long id, JsonElement jsonElement);

    // DELETE
    void deleteDaily(Long id);
}
