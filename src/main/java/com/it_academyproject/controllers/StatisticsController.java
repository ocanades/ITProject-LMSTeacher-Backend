package com.it_academyproject.controllers;

import com.it_academyproject.services.StatisticsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController
{
    @Autowired
    StatisticsService statisticsService;

	@GetMapping( "/api/statistics/per-itinerary" )
    public ResponseEntity<String> perItinerary(){
    	try
        {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(statisticsService.perItinerary().toString());
        }
        catch (Exception e)
        {
            String exceptionMessage = e.getMessage();
            JSONObject sendData = new JSONObject();
            JSONObject message = new JSONObject();
            message.put("type" , "error");
            message.put("message" , exceptionMessage);
            sendData.put("Message" , message);
            return new ResponseEntity<String>( sendData.toString() , HttpStatus.BAD_REQUEST);
        }
    }

	@GetMapping( "/api/statistics/per-gender" )
    public ResponseEntity<String> perGender( )
    {
        try
        {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(statisticsService.perGender().toString());
        }
        catch (Exception e)
        {
            String exceptionMessage = e.getMessage();
            JSONObject sendData = new JSONObject();
            JSONObject message = new JSONObject();
            message.put("type" , "error");
            message.put("message" , exceptionMessage);
            sendData.put("Message" , message);
            return new ResponseEntity( sendData.toString() , HttpStatus.BAD_REQUEST);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping( "/api/statistics/per-absence" )
    public ResponseEntity<String> perAbsence()
    {
        try
        {
            String sendData = statisticsService.perAbsence();
            return new ResponseEntity( sendData.toString() , HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            String exceptionMessage = e.getMessage();
            JSONObject sendData = new JSONObject();
            JSONObject message = new JSONObject();
            message.put("type" , "error");
            message.put("message" , exceptionMessage);
            sendData.put("Message" , message);
            return new ResponseEntity( sendData.toString() , HttpStatus.BAD_REQUEST);
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping( "/api/statistics/finish-in-x-days" )
    public ResponseEntity<String> finishInXdays()
    {
        try
        {
            String sendData = statisticsService.finishInXdays();
            return new ResponseEntity( sendData.toString() , HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            String exceptionMessage = e.getMessage();
            JSONObject sendData = new JSONObject();
            JSONObject message = new JSONObject();
            message.put("type" , "error");
            message.put("message" , exceptionMessage);
            sendData.put("Message" , message);
            return new ResponseEntity( sendData.toString() , HttpStatus.BAD_REQUEST);
        }
    }
}

