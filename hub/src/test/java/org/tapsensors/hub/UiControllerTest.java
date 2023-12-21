package org.tapsensors.hub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tapsensors.hub.sensor.SensorRepository;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(UiController.class)
class UiControllerTest {
    @MockBean
    private SensorRepository sensorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void dashboard_notAuthenticated_isUnauthorized() throws Exception {
        when(sensorRepository.findAllByOrderByIdAsc()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void dashboard_whenAuthenticated_returns() throws Exception {
        when(sensorRepository.findAllByOrderByIdAsc()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }
}