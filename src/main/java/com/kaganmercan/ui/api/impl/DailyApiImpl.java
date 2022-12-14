package com.kaganmercan.ui.api.impl;

import com.google.gson.JsonElement;
import com.kaganmercan.business.services.IDailyService;
import com.kaganmercan.error.ApiResult;
import com.kaganmercan.exception.ResourceNotFoundException;
import com.kaganmercan.retrofit.request.IDailyServiceRequest;
import com.kaganmercan.ui.api.IDailyApi;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Lombok
@RequiredArgsConstructor

@RestController
@RequestMapping("/gateway/daily")
public class DailyApiImpl implements IDailyApi {

    // Injection
    private final IDailyService dailyService;
    private final IDailyServiceRequest dailyServiceRequest;
    private static final String PATH = "/gateway/daily";

    // CREATE
    // Localhost
    // http://localhost:1111/gateway/daily
    // EC2 Public DNS
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/gateway/daily
    @Override
    @PostMapping
    public ApiResult createDaily(@RequestBody JsonElement jsonElement) {
        dailyService.createDaily(jsonElement);
        return new ApiResult(200, "Daily post created", PATH);
    }

    // LIST
    // Localhost
    // http://localhost:1111/gateway/daily
    // EC2 Public DNS
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/gateway/daily
    @Override
    @GetMapping
    public ResponseEntity<List<?>> listDaily() {
        dailyService.listDaily();
        return ResponseEntity.ok(dailyService.listDaily());
    }

    // FIND
    // Localhost
    // http://localhost:1111/gateway/daily/1
    // EC2 Public DNS
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/gateway/daily/1
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findDaily(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(dailyService.findDaily(id));
    }

    // TODO: return null but status 200,
    // UPDATE
    // Localhost
    // http://localhost:1111/gateway/daily/1
    // EC2 Public DNS
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/gateway/daily/1
    @Override
    @PutMapping("/{id}")
    public ApiResult updateDaily(@PathVariable(name = "id") Long id, @RequestBody JsonElement jsonElement) {
        dailyService.updateDaily(id, jsonElement);
        return new ApiResult(200, "Daily updated", PATH);
    }

    // DELETE
    // Localhost
    // http://localhost:1111/gateway/daily/1
    // EC2 Public DNS
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/gateway/daily/1
    @Override
    @DeleteMapping("/{id}")
    public ApiResult deleteDaily(@PathVariable(name = "id") Long id) {
        dailyService.deleteDaily(id);
        return new ApiResult(200, "Daily deleted", PATH);
    }
}
