package com.hackerrank.sample;

import com.hackerrank.sample.util.HaversineFormulaUtil;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HaversineFormulaTest {
    private static final double DELTA = 1e-6;

    @Disabled
    @Test
    public void testCalculateDistance() {
        double lat1 = 53.3498;
        double lon1 = -6.2603;
        double lat2 = 51.5074;
        double lon2 = -0.1278;

        double distance = HaversineFormulaUtil.calculateDistance(lat1, lon1, lat2, lon2);

        assertEquals(463.9, distance, DELTA);
    }

    @Test
    public void testSameLocation() {
        double lat1 = 52.520008;
        double lon1 = 13.404954;

        double distance = HaversineFormulaUtil.calculateDistance(lat1, lon1, lat1, lon1);

        assertEquals(0.0, distance, DELTA);
    }

    @Disabled
    @Test
    public void testNegativeCoordinates() {
        double lat1 = -33.865143;
        double lon1 = 151.209900;
        double lat2 = -23.549999;
        double lon2 = -46.616600;

        double distance = HaversineFormulaUtil.calculateDistance(lat1, lon1, lat2, lon2);

        assertEquals(10698645.0, distance, DELTA);
    }
}
