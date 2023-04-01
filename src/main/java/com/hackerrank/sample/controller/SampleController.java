package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("/defaultHello")
    public ResponseEntity<StringResponse> defaultHello(
            @RequestParam(name = "message", required = false) String message) {
        String finalMessage = message != null ? "Hello "+message : "Hello World!";
        return ResponseEntity.ok(new StringResponse(finalMessage));
    }


    @PostMapping("/customHello")
    public ResponseEntity<StringResponse> customHello(
            @RequestParam(name = "message", required = false) String message
    ) {
        if(message == null) return ResponseEntity.badRequest().build();

        String finalMessage = "Custom "+message.trim();
        return ResponseEntity.ok(new StringResponse(finalMessage));
    }

}
