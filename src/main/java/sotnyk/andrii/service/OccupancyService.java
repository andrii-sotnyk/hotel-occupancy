package sotnyk.andrii.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sotnyk.andrii.entity.RoomOccupancy;
import sotnyk.andrii.entity.RoomType;

@Service
public class OccupancyService {

    private static final Logger logger = LoggerFactory.getLogger(OccupancyService.class);

    private List<Double> prices = new ArrayList<>();

    //TODO: read the prices from e.g. a database
    @PostConstruct
    public void initPrices() {
        this.prices = List.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0);
    }

    public Map<RoomType, RoomOccupancy> getOptimalOccupancy(int freePremiumRooms, int freeEconomyRooms) {
        logger.info("Free rooms: Premium = {}, Economy = {}", freePremiumRooms, freeEconomyRooms);
        logger.info("Customer prices: {}", prices);
        List<Double> premiumRooms = new ArrayList<>();
        List<Double> economyRooms = new ArrayList<>();

        //TODO: insert occupancy calculation logic here

        double premiumRent = premiumRooms.stream().mapToDouble(p -> p).sum();
        double economyRent = economyRooms.stream().mapToDouble(p -> p).sum();
        logger.info("Premium rooms: {}, rent = {}", premiumRooms, premiumRent);
        logger.info("Economy rooms: {}, rent = {}", economyRooms, economyRent);

        return Map.of(  RoomType.PREMIUM, new RoomOccupancy(premiumRooms.size(), premiumRent),
                        RoomType.ECONOMY, new RoomOccupancy(economyRooms.size(), economyRent));
    }
}
