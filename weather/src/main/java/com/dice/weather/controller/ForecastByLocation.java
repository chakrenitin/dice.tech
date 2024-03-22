package com.dice.weather.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/forecast/{location}")
public class ForecastByLocation {

    @Value("${rapidapi.baseurl}")
    private String rapidApiBaseUrl;

    @Value("${rapidapi.X-RapidAPI-Key}")
    private String X_RapidAPI_Key;

    @Value("${rapidapi.X-RapidAPI-Host}")
    private String X_RapidAPI_Host;

    @GetMapping("/summary")
    public ResponseEntity<String> getForecastByLocation(@PathVariable("location") String location) throws UnirestException {
        try {
            HttpResponse<String> response = Unirest.get(rapidApiBaseUrl + "/rapidapi/forecast/" + location + "/summary/")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .asString();

            if(response.getStatus() == 200) {
                return ResponseEntity.ok(response.getBody().toString());
            }
            return ResponseEntity.status(response.getStatus()).body(response.getStatusText());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

    @GetMapping("/hourly")
    public ResponseEntity<String> getHourlyForecastByLocation(@PathVariable("location") String location) throws UnirestException {
        try{
            HttpResponse<String> response = Unirest.get(rapidApiBaseUrl + "/rapidapi/forecast/"+location+"/hourly/")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .asString();

            if(response.getStatus() == 200) {
                return ResponseEntity.ok(response.getBody().toString());
            }

            return ResponseEntity.status(response.getStatus()).body(response.getStatusText());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
}
