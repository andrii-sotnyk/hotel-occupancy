package sotnyk.andrii.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sotnyk.andrii.entity.RoomOccupancy;
import sotnyk.andrii.entity.RoomType;
import sotnyk.andrii.service.OccupancyService;

@RestController
public class OccupancyController {

    private final OccupancyService occupancyService;

    public OccupancyController(OccupancyService occupancyService) {
        this.occupancyService = occupancyService;
    }

    @GetMapping("/occupancy")
    public Map<RoomType, RoomOccupancy> getOptimalOccupancy(@RequestParam int premium, @RequestParam int economy) {
        return occupancyService.getOptimalOccupancy(premium, economy);
    }

}
