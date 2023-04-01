package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.HotelResponse;
import com.hackerrank.sample.entity.Hotel;
import com.hackerrank.sample.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(new HotelResponse(hotel.getName(), hotel.getAddress(), hotel.getLatitude(), hotel.getLongitude()), HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
    }


    // Endpoint to get all hotels closest to fartest  to the city center
    @GetMapping("/search/{cityId}")
    public List<HotelResponse> getHotelsClosestToCityCenter(@PathVariable String cityId) {
        return mappingToHotelResponse(hotelService.findNearestToFartestHotels(cityId));
    }

    private List<HotelResponse> mappingToHotelResponse(List<Hotel> nearestHotels) {
        return nearestHotels.stream().map(it -> new HotelResponse(it.getName(), it.getAddress(), it.getLatitude(), it.getLongitude(), it.getDistance())).collect(Collectors.toList());
    }

    @GetMapping("/search2/{cityId}")
    public List<HotelResponse> getHotelsClosest(@PathVariable String cityId) {
        return mappingToHotelResponse(hotelService.findNearestHotel(cityId));
    }

}
