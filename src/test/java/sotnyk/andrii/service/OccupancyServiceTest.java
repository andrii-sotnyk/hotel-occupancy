package sotnyk.andrii.service;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sotnyk.andrii.entity.RoomOccupancy;
import sotnyk.andrii.entity.RoomType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupancyServiceTest {
    private static final OccupancyService occupancyService = new OccupancyService();

    @BeforeAll
    public static void initService() {
        occupancyService.initPrices();
    }

    @Test
    public void test1() {
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(3, 3);
        assertEquals(3, response.get(RoomType.PREMIUM).rooms());
        assertEquals(738.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(3, response.get(RoomType.ECONOMY).rooms());
        assertEquals(167.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    public void test2() {
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(7, 5);
        assertEquals(6, response.get(RoomType.PREMIUM).rooms());
        assertEquals(1054.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(4, response.get(RoomType.ECONOMY).rooms());
        assertEquals(189.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    public void test3() {
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(2, 7);
        assertEquals(2, response.get(RoomType.PREMIUM).rooms());
        assertEquals(583.0, response.get(RoomType.PREMIUM).rent());
        assertEquals(4, response.get(RoomType.ECONOMY).rooms());
        assertEquals(189.99, response.get(RoomType.ECONOMY).rent());
    }

    @Test
    public void test4() {
        Map<RoomType, RoomOccupancy> response = occupancyService.getOptimalOccupancy(7, 1);
        assertEquals(7, response.get(RoomType.PREMIUM).rooms());
        assertEquals(1153.99, response.get(RoomType.PREMIUM).rent());
        assertEquals(1, response.get(RoomType.ECONOMY).rooms());
        assertEquals(45.0, response.get(RoomType.ECONOMY).rent());
    }
}
