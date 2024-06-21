package sotnyk.andrii.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sotnyk.andrii.HotelOccupancyManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelOccupancyManager.class)
@AutoConfigureMockMvc
public class OccupancyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHappyPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/occupancy")
                        .queryParam("premium", "3")
                        .queryParam("economy", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"ECONOMY\":{\"rooms\":3,\"rent\":167.99},\"PREMIUM\":{\"rooms\":3,\"rent\":738.0}}"));
    }

    @Test
    public void testMissingParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/occupancy")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException));
    }

    @Test
    public void testWrongTypeParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/occupancy")
                        .queryParam("premium", "3")
                        .queryParam("economy", "three")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    public void testNegativeParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/occupancy")
                        .queryParam("premium", "3")
                        .queryParam("economy", "-3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"ECONOMY\":{\"rooms\":0,\"rent\":0.0},\"PREMIUM\":{\"rooms\":3,\"rent\":738.0}}"));
    }
}
