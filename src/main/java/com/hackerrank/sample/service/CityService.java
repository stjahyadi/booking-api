package com.hackerrank.sample.service;

import com.hackerrank.sample.entity.City;
import com.hackerrank.sample.exception.CityNotFoundException;
import com.hackerrank.sample.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    public City getCity(String cityId) {
//        return cityRepository.findById(cityId).orElseThrow(() -> {
//                throw new CityNotFoundException("City with id "+cityId+" is not found!");
//        });

        return cityRepository.findById(cityId).
                <CityNotFoundException>orElseThrow(() -> {
                    throw new CityNotFoundException("City with id "+cityId+" is not found!");
        });
    }
}
