package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.HotelResponse;
import com.hackerrank.sample.entity.City;
import com.hackerrank.sample.entity.Hotel;
import com.hackerrank.sample.exception.HotelNotFoundException;
import com.hackerrank.sample.repository.HotelRepository;
import com.hackerrank.sample.util.HaversineFormulaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityService cityService;

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> {
            return new HotelNotFoundException("Hotel with id " + id + " is not found!");
        });
    }

    public void deleteHotelById(Long id) {
        Hotel hotel = getHotelById(id);
        // This is soft delete
        hotel.setDeleted(true);
        hotelRepository.save(hotel);
    }

    public List<Hotel> findNearestToFartestHotels(String cityId) {
        // Find the city based on cityId
        City city = cityService.getCity(cityId);
        double cityLat = city.getLatitude();
        double cityLon = city.getLongitude();

        // Find all hotels
        List<Hotel> hotels = hotelRepository.findAll();
        // Find the distance between the city and each hotel using the Haversine formula
        for (Hotel hotel : hotels) {
            double hotelLat = hotel.getLatitude();
            double hotelLon = hotel.getLongitude();
            double distance = HaversineFormulaUtil.calculateDistance(cityLat, cityLon, hotelLat, hotelLon);

        }

        // Sort the hotels by distance
        hotels.sort((h1, h2) -> Double.compare(h1.getDistance(), h2.getDistance()));

        // Return the nearest hotel to the city center
        return hotels;
    }

    public List<Hotel> findNearestHotel(String cityId) {
        City city = cityService.getCity(cityId);
        List<Hotel> hotelsClosestToCityCenter = new ArrayList<>();
        double minDistance = Double.MAX_VALUE;

        // Calculate the distance between each hotel and the city center using the Haversine formula
        for (Hotel hotel : hotelRepository.findAll()) {
            double distance = HaversineFormulaUtil.calculateDistance(city.getLatitude(), city.getLongitude(), hotel.getLatitude(), hotel.getLongitude());

            // If the distance is less than the current minimum, update the minimum distance and clear the list of hotels closest to the city center
            if (distance < minDistance) {
                minDistance = distance;
                hotelsClosestToCityCenter.clear();
            }
            // If the distance is equal to the current minimum, add the hotels to the list of hotels closest to the city center
            if (distance == minDistance) {
                hotel.setDistance(distance);
                hotelsClosestToCityCenter.add(hotel);
            }
        }
        // Sort the hotels by distance
        hotelsClosestToCityCenter.sort((h1, h2) -> Double.compare(h1.getDistance(), h2.getDistance()));
        return hotelsClosestToCityCenter;
    }
}
