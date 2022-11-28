package com.kaganmercan.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IDailyServiceRequest {
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

    //CREATE
    @POST("/api/v1/daily/create")
    Call<JsonElement> createDaily(@Body JsonElement jsonElement);

    // LIST
    @GET("/api/v1/daily/list")
    Call<List<JsonElement>> listDaily();

    // FIND
    @GET("/api/v1/daily/find/{id}")
    Call<JsonElement> findDaily(@Path("id") Long id);

    // UPDATE
    @PUT("/api/v1/daily/update/{id}")
    Call<JsonElement> updateDaily(@Path("id") Long id, @Body JsonElement jsonElement);

    // DELETE
    @DELETE("/api/v1/daily/delete/{id}")
    Call<JsonElement> deleteDaily(@Path("id") Long id);


}
