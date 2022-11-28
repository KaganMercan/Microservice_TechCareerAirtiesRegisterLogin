package com.kaganmercan.business.services.impl;

import com.google.gson.JsonElement;
import com.kaganmercan.business.services.IDailyService;
import com.kaganmercan.retrofit.RetrofitCommonGenerics;
import com.kaganmercan.retrofit.request.IDailyServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

// Lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class DailyServiceImpl implements IDailyService {
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


    // Injection
    private final IDailyServiceRequest dailyServiceRequest;

    // CREATE
    @Override
    public JsonElement createDaily(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.createDaily(jsonElement));
    }

    // LIST
    @Override
    public List<JsonElement> listDaily() {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.listDaily());
    }

    // FIND
    @Override
    public JsonElement findDaily(Long id) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.findDaily(id));
    }

    // UPDATE
    @Override
    public JsonElement updateDaily(Long id, JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.updateDaily(id, jsonElement));
    }

    // DELETE
    @Override
    public void deleteDaily(Long id) {
        RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.deleteDaily(id));
    }
}
