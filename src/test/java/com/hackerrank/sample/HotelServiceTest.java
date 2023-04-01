package com.hackerrank.sample;

import com.hackerrank.sample.dto.HotelResponse;
import com.hackerrank.sample.entity.City;
import com.hackerrank.sample.entity.Hotel;
import com.hackerrank.sample.exception.HotelNotFoundException;
import com.hackerrank.sample.repository.HotelRepository;
import com.hackerrank.sample.service.CityService;
import com.hackerrank.sample.service.HotelService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private CityService cityService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReturnHotelByHotelId() {
        Long hotelId = 1L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(new Hotel(hotelId, "Hotel ABC", "King St 123", 52.520067, 13.405720)));

        Hotel hotel = hotelService.getHotelById(hotelId);
        assertTrue(hotel != null);
        assertTrue(hotel.getId() == hotelId);
    }

    @Test(expected = HotelNotFoundException.class)
    public void testReturnHotelNotFoundException() {
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.empty());
        hotelService.getHotelById(123L);
    }

    @Test
    public void testDeleteHotelSuccess() {
        Long hotelId = 1L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(new Hotel(hotelId, "Hotel ABC", "King St 123", 52.520067, 13.405720)));

        hotelService.deleteHotelById(hotelId);
        Hotel deletedHotel = hotelService.getHotelById(hotelId);
        assertTrue(deletedHotel.isDeleted());
    }

    @Ignore
    @Test
    public void testFindNearestToFartestHotels() {
        String cityId = "Berlin";
        when(cityService.getCity(cityId)).thenReturn(new City("Berlin", 52.520008, 13.404954));

        List<Hotel> hotels = new ArrayList<>();
        Hotel hotelA = new Hotel(1L, "Hotel A", "King St 123", 52.520067, 13.405720);
        Hotel hotelB = new Hotel(2L, "Hotel B", "Juliana St 456", 52.519908, 13.406125);
        Hotel hotelC = new Hotel(3L, "Hotel C", "Lombok St 789", 52.519612, 13.405261);
        when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotelA, hotelB, hotelC));

        List<Hotel> hotelsClosestToCityCenter = hotelService.findNearestToFartestHotels(cityId);
        List<Hotel> expectedHotels = Arrays.asList(hotelC, hotelA, hotelB);
        assertIterableEquals(expectedHotels, hotelsClosestToCityCenter);
    }
}
