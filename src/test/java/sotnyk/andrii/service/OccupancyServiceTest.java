package sotnyk.andrii.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sotnyk.andrii.entity.RoomOccupancy;
import sotnyk.andrii.entity.RoomType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sotnyk.andrii.service.OccupancyService.DEFAULT_PRICES;

public class OccupancyServiceTest {
    private static final OccupancyService occupancyService = new OccupancyService();

    @Test
    @DisplayName("Default prices, premium = 3, economy = 3")
    public void test1() {
        occupancyService.setCustomerPrices(DEFAULT_PRICES);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(3, 3);
        assertEquals(3, response.get(RoomType.PREMIUM).rooms());
        assertEquals(738.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(3, response.get(RoomType.ECONOMY).rooms());
        assertEquals(167.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    @DisplayName("Default prices, premium = 7, economy = 5")
    public void test2() {
        occupancyService.setCustomerPrices(DEFAULT_PRICES);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(7, 5);
        assertEquals(6, response.get(RoomType.PREMIUM).rooms());
        assertEquals(1054.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(4, response.get(RoomType.ECONOMY).rooms());
        assertEquals(189.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    @DisplayName("Default prices, premium = 2, economy = 7")
    public void test3() {
        occupancyService.setCustomerPrices(DEFAULT_PRICES);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(2, 7);
        assertEquals(2, response.get(RoomType.PREMIUM).rooms());
        assertEquals(583.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(4, response.get(RoomType.ECONOMY).rooms());
        assertEquals(189.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    @DisplayName("Default prices, premium = 7, economy = 1")
    public void test4() {
        occupancyService.setCustomerPrices(DEFAULT_PRICES);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(7, 1);
        assertEquals(7, response.get(RoomType.PREMIUM).rooms());
        assertEquals(1153.99, response.get(RoomType.PREMIUM).rent());
        assertEquals(1, response.get(RoomType.ECONOMY).rooms());
        assertEquals(45.0, response.get(RoomType.ECONOMY).rent());
    }

    // Additional tests

    @Test
    @DisplayName("Empty prices, free rooms")
    public void test5() {
        occupancyService.setCustomerPrices(List.of());
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(3, 3);
        assertEquals(0, response.get(RoomType.PREMIUM).rooms());
        assertEquals(0, response.get(RoomType.PREMIUM).rent());
        assertEquals(0, response.get(RoomType.ECONOMY).rooms());
        assertEquals(0, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    @DisplayName("Default prices, empty or negative rooms")
    public void test6() {
        occupancyService.setCustomerPrices(DEFAULT_PRICES);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(0, -3);
        assertEquals(0, response.get(RoomType.PREMIUM).rooms());
        assertEquals(0, response.get(RoomType.PREMIUM).rent());
        assertEquals(0, response.get(RoomType.ECONOMY).rooms());
        assertEquals(0, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    @DisplayName("Default prices + negative or null values, premium = 3, economy = 3")
    public void test7() {
        List<Double> prices = new ArrayList<>(DEFAULT_PRICES);
        prices.add(null);
        prices.add(-50.0);
        occupancyService.setCustomerPrices(prices);
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(3, 3);
        assertEquals(3, response.get(RoomType.PREMIUM).rooms());
        assertEquals(738.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(3, response.get(RoomType.ECONOMY).rooms());
        assertEquals(167.99, response.get(RoomType.ECONOMY).rent());
    }
}
